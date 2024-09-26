package dam.a48168.chefskisses.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.data.mealSaved
import dam.a48168.chefskisses.databinding.ItemIngredientsBinding
import dam.a48168.chefskisses.databinding.ItemStepsBinding
import dam.a48168.chefskisses.databinding.MealPageBinding

class MealPage : AppCompatActivity() {

    private lateinit var binding: MealPageBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private var isMealSaved = false
    private var currentUserEmail: String? = null
    private var savedMealKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.meal_page)
        binding.lifecycleOwner = this
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        // Check if a user is authenticated
        val currentUser = auth.currentUser
        currentUserEmail = currentUser?.email
        if (currentUser == null) {
            binding.saveButton.visibility = View.INVISIBLE
        } else {
            binding.saveButton.visibility = View.VISIBLE
            checkIfMealSaved(currentUser.email ?: "", intent.getIntExtra("mealID", 1))
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val mealId = intent.getIntExtra("mealID", 1)
        fetchMealDetails(mealId)

        binding.saveButton.setOnClickListener {
            if (isMealSaved) {
                unsaveMeal()
            } else {
                saveMeal(mealId)
            }
        }
    }

    private fun checkIfMealSaved(userEmail: String, mealId: Int) {
        databaseReference.child("savedMeals")
            .orderByChild("idMeal")
            .equalTo(mealId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    isMealSaved = false
                    savedMealKey = null
                    for (savedMealSnapshot in snapshot.children) {
                        val savedMeal = savedMealSnapshot.getValue(mealSaved::class.java)
                        if (savedMeal != null && savedMeal.mail == userEmail) {
                            isMealSaved = true
                            savedMealKey = savedMealSnapshot.key
                            break
                        }
                    }
                    updateSaveButtonImage()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("MealPage", "checkIfMealSaved:onCancelled", error.toException())
                }
            })
    }

    private fun fetchMealDetails(mealId: Int) {
        databaseReference.child("recipes").orderByChild("mealID").equalTo(mealId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (recipeSnapshot in snapshot.children) {
                        val meal = recipeSnapshot.getValue(MealDetail::class.java)
                        meal?.let {
                            binding.meal = it
                            populateIngredients(it.ingredients)
                            populateSteps(it.steps)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("MealPage", "fetchMealDetails:onCancelled", error.toException())
                }
            })
    }

    private fun saveMeal(mealId: Int) {
        currentUserEmail?.let { userEmail ->
            val mealSaved = mealSaved(mealId, userEmail)
            databaseReference.child("savedMeals").push().setValue(mealSaved)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.saveButton.setImageResource(R.drawable.icons8saved50)
                        Toast.makeText(this, "Meal saved successfully!", Toast.LENGTH_SHORT).show()
                        isMealSaved = true
                        // Update savedMealKey after saving
                        databaseReference.child("savedMeals")
                            .orderByChild("idMeal")
                            .equalTo(mealId.toDouble())
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    for (savedMealSnapshot in snapshot.children) {
                                        val savedMeal = savedMealSnapshot.getValue(mealSaved::class.java)
                                        if (savedMeal != null && savedMeal.mail == userEmail) {
                                            savedMealKey = savedMealSnapshot.key
                                            break
                                        }
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Log.w("MealPage", "fetchSavedMealKey:onCancelled", error.toException())
                                }
                            })
                    } else {
                        Toast.makeText(this, "Failed to save meal.", Toast.LENGTH_SHORT).show()
                    }
                }
        } ?: run {
            Toast.makeText(this, "No authenticated user.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun unsaveMeal() {
        savedMealKey?.let { key ->
            databaseReference.child("savedMeals").child(key).removeValue()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.saveButton.setImageResource(R.drawable.icons8save72)
                        Toast.makeText(this, "Meal unsaved successfully!", Toast.LENGTH_SHORT).show()
                        isMealSaved = false
                        savedMealKey = null
                    } else {
                        Toast.makeText(this, "Failed to unsave meal.", Toast.LENGTH_SHORT).show()
                    }
                }
        } ?: run {
            Toast.makeText(this, "Failed to unsave meal.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateSaveButtonImage() {
        if (isMealSaved) {
            binding.saveButton.setImageResource(R.drawable.icons8saved50)
        } else {
            binding.saveButton.setImageResource(R.drawable.icons8save72)
        }
    }

    private fun populateIngredients(ingredients: List<String>) {
        val inflater = LayoutInflater.from(this)
        binding.ingredientsContainer.removeAllViews()
        ingredients.forEach { ingredient ->
            val ingredientBinding: ItemIngredientsBinding = ItemIngredientsBinding.inflate(inflater, binding.ingredientsContainer, false)
            ingredientBinding.ingredient = ingredient
            binding.ingredientsContainer.addView(ingredientBinding.root)
        }
    }

    private fun populateSteps(steps: List<String>) {
        val inflater = LayoutInflater.from(this)
        binding.stepsContainer.removeAllViews()
        steps.forEachIndexed { index, step ->
            val stepBinding: ItemStepsBinding = ItemStepsBinding.inflate(inflater, binding.stepsContainer, false)
            stepBinding.step = step
            stepBinding.stepNumber = index + 1
            binding.stepsContainer.addView(stepBinding.root)
        }
    }
}
