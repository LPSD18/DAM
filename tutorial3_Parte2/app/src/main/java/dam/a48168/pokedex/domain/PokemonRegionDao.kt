package dam.a48168.pokedex.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dam.a48168.pokedex.data.PokemonRegion

@Dao
interface PokemonRegionDao {
    @Query("SELECT * FROM pokemon_region")
    fun getRegions() : List<PokemonRegion>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegion(region: PokemonRegion)
    @Query("SELECT COUNT(*) FROM pokemon_region")
    fun count(): Int
}