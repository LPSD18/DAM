package dam.a48168.pokedex.data

import androidx.room.Embedded
import androidx.room.Relation

data class RegionWithPokemons(
    @Embedded
    val region: PokemonRegion,

    @Relation(
        parentColumn = "region_id",
        entityColumn = "region_id"
    )
    val pokemon: List<Pokemon>
)