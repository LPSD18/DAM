package dam.a48168.chefskisses.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.databinding.ItemIngredientsBinding
import dam.a48168.chefskisses.databinding.ItemStepsBinding
import dam.a48168.chefskisses.databinding.MealPageBinding

class MealPageAdapter(
    private val mealList: List<MealDetail>,
    private val context: Context
) : RecyclerView.Adapter<MealPageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MealPageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(meal: MealDetail) {
            binding.meal = meal

            // Clear previous views
            binding.ingredientsContainer.removeAllViews()
            binding.stepsContainer.removeAllViews()

            // Dynamically add ingredients
            meal.ingredients.forEach { ingredient ->
                val ingredientBinding: ItemIngredientsBinding = ItemIngredientsBinding.inflate(
                    LayoutInflater.from(context), binding.ingredientsContainer, false)
                ingredientBinding.ingredient = ingredient
                binding.ingredientsContainer.addView(ingredientBinding.root)
            }

            // Dynamically add steps
            meal.steps.forEachIndexed { index, step ->
                val stepBinding: ItemStepsBinding = ItemStepsBinding.inflate(
                    LayoutInflater.from(context), binding.stepsContainer, false)
                stepBinding.step = step
                stepBinding.stepNumber = index + 1
                binding.stepsContainer.addView(stepBinding.root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = MealPageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.bindView(meal)
    }
}
