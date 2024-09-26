package dam.a48168.chefskisses.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.MealDetail
import java.util.*

class AddPage : BottomNav() {

    private lateinit var ingredientsContainer: LinearLayout
    private lateinit var stepsContainer: LinearLayout
    private lateinit var imageViewRecipe: ImageView
    private lateinit var imageUri: Uri

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: FirebaseStorage
    private lateinit var auth: FirebaseAuth

    override val contentViewId: Int
        get() = R.layout.add_page
    override val navigationMenuItemId: Int
        get() = R.id.add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().getReference("recipes")
        storageReference = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        ingredientsContainer = findViewById(R.id.ingredientsContainer)
        stepsContainer = findViewById(R.id.stepsContainer)
        imageViewRecipe = findViewById(R.id.imageViewRecipe)
        val buttonAddIngredient: Button = findViewById(R.id.buttonAddIngredient)
        val buttonAddStep: Button = findViewById(R.id.buttonAddStep)
        val buttonAddImage: Button = findViewById(R.id.buttonAddImage)

        buttonAddIngredient.setOnClickListener {
            addIngredientEditText()
        }
        buttonAddStep.setOnClickListener {
            addStepEditText()
        }
        buttonAddImage.setOnClickListener {
            selectImage()
        }

        val submit: Button = findViewById(R.id.buttonSubmit)
        submit.setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextRecipeName).text.toString()
            val type = findViewById<Spinner>(R.id.spinnerRecipeType).selectedItem.toString()
            val description = findViewById<EditText>(R.id.editTextDescription).text.toString()
            val ingredients = getDynamicEditTextValues(ingredientsContainer)
            val steps = getDynamicEditTextValues(stepsContainer)

            fetchHighestIdAndSaveRecipe(name, type, description, ingredients, steps)
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data!!
            imageViewRecipe.setImageURI(imageUri)
        }
    }

    private fun fetchHighestIdAndSaveRecipe(name: String, type: String, description: String, ingredients: List<String>, steps: List<String>) {
        databaseReference.get().addOnSuccessListener { snapshot ->
            var maxId = 0
            for (recipeSnapshot in snapshot.children) {
                val recipe = recipeSnapshot.getValue(MealDetail::class.java)
                if (recipe != null && recipe.mealID > maxId) {
                    maxId = recipe.mealID
                }
            }
            val newId = maxId + 1
            if (::imageUri.isInitialized) {
                uploadImageAndSaveRecipe(newId, name, type, description, ingredients, steps)
            } else {
                saveRecipe(newId, name, type, description, ingredients, steps, null)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch recipes", Toast.LENGTH_LONG).show()
        }
    }

    private fun uploadImageAndSaveRecipe(newId: Int, name: String, type: String, description: String, ingredients: List<String>, steps: List<String>) {
        val storageRef = storageReference.reference.child("recipe_images/${UUID.randomUUID()}")
        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    saveRecipe(newId, name, type, description, ingredients, steps, uri.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to upload image", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveRecipe(newId: Int, name: String, type: String, description: String, ingredients: List<String>, steps: List<String>, imageUrl: String?) {
        val recipeId = databaseReference.push().key ?: return
        val email = auth.currentUser?.email.toString()
        val recipe = MealDetail(newId, email, name, type, ingredients, steps, description, imageUrl)
        databaseReference.child(recipeId).setValue(recipe)
            .addOnSuccessListener {
                Toast.makeText(this, "Recipe saved", Toast.LENGTH_LONG).show()
                intent = Intent(this, SearchPage::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save recipe", Toast.LENGTH_LONG).show()
            }
    }

    private fun addIngredientEditText() {
        val editText = EditText(this)
        editText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editText.hint = "Ingredient"
        ingredientsContainer.addView(editText)
    }

    private fun addStepEditText() {
        val editText = EditText(this)
        editText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editText.hint = "Step"
        stepsContainer.addView(editText)
    }

    private fun getDynamicEditTextValues(container: LinearLayout): List<String> {
        val values = mutableListOf<String>()
        for (i in 0 until container.childCount) {
            val view = container.getChildAt(i)
            if (view is EditText) {
                values.add(view.text.toString())
            }
        }
        return values
    }
}
