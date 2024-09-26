package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.adapters.MealAdapter
import dam.a48168.chefskisses.viewmodel.MealsViewModel
import dam.a48168.chefskisses.databinding.MealsPageBinding
import androidx.viewbinding.ViewBinding
import dam.a48168.chefskisses.adapters.OnMealClickListener
import dam.a48168.chefskisses.data.MealDetail


class Meals : AppCompatActivity(), OnMealClickListener {

    private val viewModel: MealsViewModel by viewModels()
    lateinit var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = DataBindingUtil.setContentView(this,R.layout.meals_page)
        val mealsBinding = binding as MealsPageBinding

        mealsBinding.backButton.setOnClickListener{
            onBackPressed()
        }

        var listView = mealsBinding.mealsRecyclerView
        val type = intent.getStringExtra("type").toString()
        if (type!=null){
            viewModel.fetchMeals(type)
        }

        mealsBinding.recipeTypeTextView.text = type

        viewModel.meals.observe(this){
            listView.adapter = it?.let { it1->
                MealAdapter(it1,this,this)
            }
        }
    }

    override fun onMealClick(meal: MealDetail) {
        val intent = Intent(this,MealPage::class.java)
        intent.putExtra("mealID",meal.mealID)
        startActivity(intent)
    }
}
