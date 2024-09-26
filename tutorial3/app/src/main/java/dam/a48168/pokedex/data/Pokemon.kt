package dam.a48168.pokedex.data

import android.os.Parcel
import android.os.Parcelable

class Pokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var region: PokemonRegion,
    var type: List<PokemonType>)
{

    data class Pokemon(
        var id: Int,
        var name: String,
        var imageUrl: String,
        var region: PokemonRegion,
        var type: List<PokemonType>
    )

}