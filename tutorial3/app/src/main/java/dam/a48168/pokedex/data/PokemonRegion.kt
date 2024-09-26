package dam.a48168.pokedex.data

import androidx.annotation.DrawableRes

class PokemonRegion(
    var id: Int,
    var name: String,
    @DrawableRes val bg : Int,
    @DrawableRes val starters : Int
) {

    data class PokemonRegion(
        var id: Int,
        var name: String,
        @DrawableRes val bg : Int,
        @DrawableRes val starters : Int
    )


}