package dam.a48168.pokedex.data

class PokemonDetail(
    var idPokemon: Int,
    var name: String,
    var imageUrl: String,
    var description: String,
    var type: List<PokemonType>,
    var weight: Float,
    var Height: Float,
//    var category: Float,
    var Ability: String,
    var Evolution: Int,
    var Hp: Int

) {

    data class PokemonDetail(
        var idPokemon: Int,
        var name: String,
        var imageUrl: String,
        var description: String,
        var type: List<PokemonType>,
        var weight: Float,
        var Height: Float,
//    var category: Float,
        var Ability: String,
        var Evolution: Int,
        var Hp: Int
    )

}