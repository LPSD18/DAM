package dam.a48168.chefskisses.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.IngredientType

interface OnTypeClickListener{
    fun onTypelick(type: IngredientType)
}

class TypeAdapter(
    private val typeList: List<IngredientType>,
    private val context: Context,
    private val clickListener: OnTypeClickListener
): RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val bgImageView = itemView.findViewById<AppCompatImageView>(R.id.typeBgImage)
        val typeNameTextView = itemView.findViewById<AppCompatTextView>(R.id.regionNameTextView)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            adapterPosition.takeIf { it!=RecyclerView.NO_POSITION }?.let {
                val clickedType = typeList[it]
                clickListener.onTypelick(clickedType)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_ingredient,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = typeList[position]
        holder.bgImageView.setImageResource(type.bg)
        holder.typeNameTextView.text = type.name
    }


}