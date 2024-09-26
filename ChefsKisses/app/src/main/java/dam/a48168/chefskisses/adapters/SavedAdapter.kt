package dam.a48168.chefskisses.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.databinding.ItemMealBinding

class SavedAdapter(
    private val mealList: List<MealDetail>,
    private val context: Context,
    private val clickListener: OnMealClickListener
): RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val mealsItemBinding = ItemMealBinding.bind(itemView)
        fun bindView(meal: MealDetail, itemClickListener: OnMealClickListener){
            mealsItemBinding.meal = meal
            itemView.setOnClickListener{
                itemClickListener?.onMealClick(meal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_meal,parent,false)
        return SavedAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: SavedAdapter.ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.bindView(meal,clickListener)
    }
}