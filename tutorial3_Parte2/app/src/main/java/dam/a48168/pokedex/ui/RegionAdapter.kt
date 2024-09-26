package dam.a48168.pokedex.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.databinding.ItemRegionBinding

interface OnRegionClickListener {
    fun onRegionClick(region: PokemonRegion)
    fun invoke(region: PokemonRegion)
}

class RegionAdapter(
    private val pkRegionList: List<PokemonRegion>,
    private val context: Context,
    private val clickListener: OnRegionClickListener
) : RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val regionItemBinding = ItemRegionBinding.bind(itemView)
        fun bindView(region: PokemonRegion, itemClickedListener: OnRegionClickListener?) {
            regionItemBinding.region = region
            itemView.setOnClickListener{
                itemClickedListener?.invoke(region)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_region, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val region = pkRegionList[position]
        holder.bindView(region, clickListener)
    }
    override fun getItemCount(): Int {
        return pkRegionList.size
    }
}