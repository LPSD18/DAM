package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.adapters.HomeAdapter
import dam.a48168.chefskisses.viewmodel.MealsViewModel
import dam.a48168.chefskisses.adapters.OnMealClickListener
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.databinding.HomePageBinding

class HomePage : BottomNav(), OnMealClickListener {

    override val contentViewId: Int
        get() = R.layout.home_page

    override val navigationMenuItemId: Int
        get() = R.id.home

    private val viewModel: MealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mealsBinding = binding as HomePageBinding
        val listView = mealsBinding.daymealsRecyclerView
        viewModel.fetchAllMeals()

        viewModel.meals.observe(this){
            listView.adapter = it?.let { meals->
                HomeAdapter(meals,this,this)
            }
        }

    }

    override fun onMealClick(meal: MealDetail) {
        val intent = Intent(this,MealPage::class.java)
        intent.putExtra("mealID",meal.mealID)
        startActivity(intent)
    }
}
