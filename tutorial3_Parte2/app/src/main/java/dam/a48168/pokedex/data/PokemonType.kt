package dam.a48168.pokedex.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

class PokemonType(
    var id : Int,
    var name : String,
    @DrawableRes val icon : Int,
    @ColorRes val color : Int
) {

    data class PokemonType(
        var id : Int,
        var name : String,
        @DrawableRes val icon : Int,
        @ColorRes val color : Int
    )

}