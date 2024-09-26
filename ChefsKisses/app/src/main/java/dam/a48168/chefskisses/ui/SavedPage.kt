package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.viewmodel.MealsViewModel
import dam.a48168.chefskisses.adapters.OnMealClickListener
import dam.a48168.chefskisses.adapters.SavedAdapter
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.databinding.SavedPageBinding

class SavedPage: BottomNav(), OnMealClickListener{

    override val contentViewId: Int
        get() = R.layout.saved_page

    override val navigationMenuItemId: Int
        get() = R.id.save

    private val viewModel: MealsViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = FirebaseAuth.getInstance()
        val mealsBinding = binding as SavedPageBinding
        val listView = mealsBinding.daymealsRecyclerView
        viewModel.fetchSavedMeals(auth.currentUser?.email.toString())

        viewModel.meals.observe(this){
            listView.adapter = it?.let { meals->
                SavedAdapter(meals,this,this)
            }
        }

    }

    override fun onMealClick(meal: MealDetail) {
        val intent = Intent(this,MealPage::class.java)
        intent.putExtra("mealID",meal.mealID)
        startActivity(intent)
    }
}