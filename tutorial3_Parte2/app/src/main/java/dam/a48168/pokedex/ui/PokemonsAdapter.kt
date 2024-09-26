package dam.a48168.pokedex.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.Pokemon
import dam.a48168.pokedex.databinding.ItemPokemonBinding


interface OnPokemonClickListener{
    fun onPokemonClick(pokemon: Pokemon)
    fun invoke(pokemon: Pokemon)
}

class PokemonsAdapter(
    private val pokemonList: List<Pokemon>,
    private val context: Context,
    private val clickListener: OnPokemonClickListener
) : RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val pokemonItemBinding = ItemPokemonBinding.bind(itemView)
        fun bindView(pokemon: Pokemon, itemClickedListener: OnPokemonClickListener){
            pokemonItemBinding.pokemon = pokemon
            itemView.setOnClickListener{
                itemClickedListener?.invoke(pokemon)}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonsAdapter.ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindView(pokemon,clickListener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}