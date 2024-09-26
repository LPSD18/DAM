package dam.a48168.pokedex.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import dam.a48168.pokedex.R
import dam.a48168.pokedex.model.MockData
import com.squareup.picasso.Picasso;

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pokemon_detail)
        var pokemonID = intent.getIntExtra("pokemonId",1)
        if(pokemonID!=1){
            if(pokemonID!=4){
                pokemonID=1
            }
        }
        val pokemonList = MockData.pokemonDetail.filter { it.idPokemon==pokemonID }
        val pokemon = pokemonList[0]
        val image: ImageView = findViewById(R.id.imageView)
        val name: TextView = findViewById(R.id.name)
        val id: TextView = findViewById(R.id.numberPokemon)
        val description: TextView = findViewById(R.id.Description)
        val cardView: CardView = findViewById(R.id.cardView2)
        val Weight: TextView = findViewById(R.id.WeightChange)
        val Height: TextView = findViewById(R.id.HeightChange)
        val Hp: TextView = findViewById(R.id.HpChange)
        val Ability: TextView = findViewById(R.id.AbilityChange)
        val type1: ImageView = findViewById(R.id.type1Image)
        val type2: ImageView = findViewById(R.id.type2Image)
        Picasso.get().load(pokemon.imageUrl).into(image)
        name.text=pokemon.name
        id.text = "#"+ pokemonID.toString()
        description.text=pokemon.description
        Weight.text=pokemon.weight.toString()+"kg"
        Height.text=pokemon.Height.toString()+"m"
        Hp.text=pokemon.Hp.toString()
        Ability.text=pokemon.Ability
        val type = pokemon.type[0].name
        val colorResourceId: Int? = MockData.typeColorMap[type]
        if (colorResourceId != null) {
            val backColor = ContextCompat.getColor(this,colorResourceId)
            cardView.setCardBackgroundColor(backColor)
        }
        type1.setImageResource(pokemon.type[0].icon)
        if(pokemon.type.size>1){
            type2.setImageResource(pokemon.type[1].icon)
        }
        else{
            type2.visibility=View.INVISIBLE
        }

    }
}