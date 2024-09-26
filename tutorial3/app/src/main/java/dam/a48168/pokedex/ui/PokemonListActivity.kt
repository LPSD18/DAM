package dam.a48168.pokedex.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.Pokemon
import dam.a48168.pokedex.model.MockData

class PokemonListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pokemonlist)
        var listView = findViewById<RecyclerView>(R.id.pksRecyclerView)
        val regionID = intent.getIntExtra("regionId",0)
        val newList = MockData.pokemons.filter { it.region.id == regionID }

        listView.adapter = PokemonsAdapter(pokemonList = newList, context = this)
    }
}