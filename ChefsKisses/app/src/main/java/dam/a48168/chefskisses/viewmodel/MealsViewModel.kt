package dam.a48168.chefskisses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.data.mealSaved

class MealsViewModel : ViewModel() {
    private val _meals = MutableLiveData<List<MealDetail>?>()
    val meals: LiveData<List<MealDetail>?>
        get() = _meals

    private val databaseReference = FirebaseDatabase.getInstance().getReference("recipes")
    private val savedMealsReference = FirebaseDatabase.getInstance().getReference("savedMeals")

    fun fetchMeals(type: String) {
        databaseReference.orderByChild("type").equalTo(type)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val recipes = mutableListOf<MealDetail>()
                    for (dataSnapshot in snapshot.children) {
                        val recipe = dataSnapshot.getValue(MealDetail::class.java)
                        if (recipe != null) {
                            recipes.add(recipe)
                        }
                    }
                    _meals.postValue(recipes)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle possible errors.
                    println("Database error: ${error.message}")
                }
            })
    }

    fun fetchMyMeals(email: String) {
        databaseReference.orderByChild("email").equalTo(email)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val recipes = mutableListOf<MealDetail>()
                    for (dataSnapshot in snapshot.children) {
                        val recipe = dataSnapshot.getValue(MealDetail::class.java)
                        if (recipe != null) {
                            recipes.add(recipe)
                        }
                    }
                    _meals.postValue(recipes)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle possible errors.
                    println("Database error: ${error.message}")
                }
            })
    }

    fun fetchAllMeals() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = mutableListOf<MealDetail>()
                for (dataSnapshot in snapshot.children) {
                    val recipe = dataSnapshot.getValue(MealDetail::class.java)
                    if (recipe != null) {
                        recipes.add(recipe)
                    }
                }
                _meals.postValue(recipes)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
                println("Database error: ${error.message}")
            }
        })
    }

    fun fetchSavedMeals(userEmail: String) {
        savedMealsReference.orderByChild("mail").equalTo(userEmail)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val savedMealIds = mutableListOf<Int>()
                    for (savedMealSnapshot in snapshot.children) {
                        val savedMeal = savedMealSnapshot.getValue(mealSaved::class.java)
                        if (savedMeal != null) {
                            savedMealIds.add(savedMeal.idMeal)
                        }
                    }

                    databaseReference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(mealSnapshot: DataSnapshot) {
                            val recipes = mutableListOf<MealDetail>()
                            for (meal in mealSnapshot.children) {
                                val mealDetail = meal.getValue(MealDetail::class.java)
                                if (mealDetail != null && savedMealIds.contains(mealDetail.mealID)) {
                                    recipes.add(mealDetail)
                                }
                            }
                            _meals.postValue(recipes)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            println("Database error: ${error.message}")
                        }
                    })
                }

                override fun onCancelled(error: DatabaseError) {
                    println("Database error: ${error.message}")
                }
            })
    }
}
