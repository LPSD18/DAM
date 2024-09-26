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

interface OnRegionClickListener {
    fun onRegionClick(region: PokemonRegion)
}

class RegionAdapter(
    private val pkRegionList: List<PokemonRegion>,
    private val context: Context,
    private val clickListener: OnRegionClickListener
) : RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val bgImageView = itemView.findViewById<AppCompatImageView>(R.id.regionBgImage)
        val startersImageView = itemView.findViewById<AppCompatImageView>(R.id.regionStartersImageView)
        val regionTitleTextView = itemView.findViewById<AppCompatTextView>(R.id.regionNameTextView)
        val regionSubtitleTextView = itemView.findViewById<AppCompatTextView>(R.id.regionIdTextView)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v:View?){
            adapterPosition.takeIf { it!=RecyclerView.NO_POSITION }?.let {
                val clickedRegion = pkRegionList[it]
                clickListener.onRegionClick(clickedRegion)
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
        holder.bgImageView.setImageResource(region.bg)
        holder.startersImageView.setImageResource(region.starters)
        holder.regionTitleTextView.text = region.name
        holder.regionSubtitleTextView.text = region.id.toString() + "º Generation"
    }

    override fun getItemCount(): Int {
        return pkRegionList.size
    }
}