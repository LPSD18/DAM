package dam.a48168.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a48168.pokedex.data.Pokemon
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.domain.PokemonRepository
import dam.a48168.pokedex.model.MockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<List<Pokemon>?>()
    val pokemons: LiveData<List<Pokemon>?>
        get() = _pokemons

    private lateinit var _repository: PokemonRepository
    fun initViewMode(repository: PokemonRepository) {
        _repository = repository
    }

    fun fetchPokemons(region: PokemonRegion) {
        println("fff")
        viewModelScope.launch(Dispatchers.Default) {
            val pkList = _repository.getPokemonsByRegion(region)
            pkList.value?.let { println("eee"+it.size) }
            _pokemons.postValue(pkList.value)
        }
    }
}