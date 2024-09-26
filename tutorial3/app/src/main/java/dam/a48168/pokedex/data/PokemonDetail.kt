package dam.a48168.pokedex.data

class PokemonDetail(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var description: String,
    var type: List<PokemonType>,
    var weight: Float,
    var Height: Float,
    var category: Float,
    var Ability: Float
) {

    data class PokemonDetail(
        var id:Int,
        var pokemonDetailDescription: String,
        var toList: List<PokemonType>,
        var param: Any,
        var param1: Any,
        var pokemonStats: Any?,
        var toList1: List<Unit>
    )
}