package dam.a48168.pokedex.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.Pokemon
import dam.a48168.pokedex.model.MockData
import androidx.viewbinding.ViewBinding
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.databinding.ActivityPokemonlistBinding
import dam.a48168.pokedex.domain.DBModule

class PokemonListActivity : AppCompatActivity() ,OnPokemonClickListener {

    lateinit var binding: ViewBinding
    val viewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        println("ddd")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = DataBindingUtil.setContentView(this,R.layout.activity_pokemonlist)
//        setContentView(R.layout.activity_pokemonlist)
        val pokemonBinding = binding as ActivityPokemonlistBinding
        var listView = pokemonBinding.pksRecyclerView
        val regionID = intent.getIntExtra("regionId",0)
        val region = intent.getParcelableExtra<PokemonRegion>("region")
//        val newList = MockData.pokemons.filter { it.regionId == regionID }
        viewModel.initViewMode(DBModule.getInstance(this).pokemonRepository)
        viewModel.pokemons.observe(this){
            listView.adapter= it?.let{it1 ->
                PokemonsAdapter(it1,this,this)
            }
        }
        if (region != null) {
            println("hhh" + region.name)
        }
        if (region != null) {
            println("ggg")
            viewModel.fetchPokemons(region)
        }

    }

    override fun onPokemonClick(pokemon: Pokemon) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("pokemonId",pokemon.id)
        startActivity(intent)
    }

    override fun invoke(pokemon: Pokemon) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("pokemonId",pokemon.id)
        startActivity(intent)
    }


}