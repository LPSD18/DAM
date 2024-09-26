package dam.a48168.pokedex.model

import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.Pokemon
import dam.a48168.pokedex.data.PokemonDetail
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.data.PokemonStats
import dam.a48168.pokedex.data.PokemonType
import kotlin.math.roundToInt
import kotlin.random.Random

object MockData {

    private val POKEMONS_SIZE = 100

    private var pokemonDetailDescription: String = "Pokem ipsum dolor " +
            "sit amet Crustle Grotle" +
            " Dragonair Palkia Shellder Terrakion. " +
            "Hive Badge Pokeball Spinda Seedot James Vullaby " +
            "Helix Fossil. Water Gun Professor Oak Marowak Spearow " +
            "Dunsparce Chimchar Nidorino." +
            " Silver Azumarill Tyranitar Trubbish " +
            "Fighting sunt in culpa qui officia Mothim. " +
            "Celadon City Mantine Clefable Piplup Scizor " +
            "excepteur sint occaecat cupidatat non proident Terrakion."


    var regions = listOf<PokemonRegion>(
        PokemonRegion(1, "Kanto", R.drawable.bg_kanto, R.drawable.pk_kanto),
        PokemonRegion(2, "Johto", R.drawable.bg_johto, R.drawable.pk_johto),
        PokemonRegion(3, "Hoenn", R.drawable.bg_hoenn, R.drawable.pk_hoenn),
        PokemonRegion(4, "Sinnoh", R.drawable.bg_sinnoh, R.drawable.pk_sinnoh),
        PokemonRegion(5, "Unova", R.drawable.bg_unova, R.drawable.pk_unova),
        PokemonRegion(6, "Kalos", R.drawable.bg_kalos, R.drawable.pk_kalos),
        PokemonRegion(7, "Alola", R.drawable.bg_alola, R.drawable.pk_alola),
        PokemonRegion(8, "Galar", R.drawable.bg_galar, R.drawable.pk_galar),
    )

    var pokemonTypeMock= listOf<PokemonType>(
        PokemonType(1,"water", R.drawable.water, R.color.water),
        PokemonType(2,"fire", R.drawable.fire, R.color.fire),
        PokemonType(3,"bug", R.drawable.bug, R.color.bug),
        PokemonType(4,"ghost", R.drawable.ghost, R.color.ghost),
        PokemonType(5,"grass", R.drawable.grass, R.color.grass),
        PokemonType(6,"ground", R.drawable.ground, R.color.ground),
        PokemonType(7,"rock", R.drawable.rock, R.color.rock),
        PokemonType(8,"dark", R.drawable.dark, R.color.dark),
        PokemonType(9,"dragon", R.drawable.dragon, R.color.dragon),
        PokemonType(10,"electric", R.drawable.electric, R.color.electric),
        PokemonType(11,"fairy", R.drawable.fairy, R.color.fairy),
        PokemonType(12,"fighting", R.drawable.fighting, R.color.fighting),
        PokemonType(13,"ice", R.drawable.ice, R.color.ice),
        PokemonType(14,"normal", R.drawable.normal, R.color.normal),
        PokemonType(15,"psychic", R.drawable.psychic, R.color.psychic),
        PokemonType(16,"flying", R.drawable.flying, R.color.flying),
        PokemonType(17,"poison", R.drawable.poison, R.color.poison),
        PokemonType(18,"steel", R.drawable.steel, R.color.steel)
    )

    /*var pokemons = (1..POKEMONS_SIZE).map {
        Pokemon(it,
            "bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/${it}.png",
            regions.random(), pokemonTypeMock.asSequence().shuffled().take(2).toList()
        )
    }*/

    var pokemons = listOf(
         Pokemon(1,
             "bulbasaur",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/${1}.png",
             regions.get(0), listOf(pokemonTypeMock[2], pokemonTypeMock[16])

         ),
         Pokemon(4,
             "charmander",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/4.png",
             regions.get(0), listOf(pokemonTypeMock[1])
         ),
         Pokemon(6,
             "squirtle",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/7.png",
             regions.get(0), listOf(pokemonTypeMock[0])
         ),
         Pokemon(10,
             "caterpie",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/10.png",
             regions.get(0), listOf(pokemonTypeMock[2])
             ),
         Pokemon(13,
             "weedle",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/13.png",
        regions.get(0), listOf(pokemonTypeMock[2], pokemonTypeMock[16])
         ),
         Pokemon(16,
             "pidgey",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/16.png",
             regions.get(0), listOf(pokemonTypeMock[13], pokemonTypeMock[15])
         ),
         Pokemon(19,
             "rattata",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/19.png",
                     regions.get(0), listOf(pokemonTypeMock[13])
         ),
         Pokemon(21,
             "spearow",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/21.png",
             regions.get(0), listOf(pokemonTypeMock[13], pokemonTypeMock[15])
         ),
         Pokemon(23,
             "ekans",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/23.png",
             regions.get(0), listOf(pokemonTypeMock[16])
         ),
         Pokemon(25,
             "pikachu",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/25.png",
             regions.get(0), listOf(pokemonTypeMock[9])
         ),
         Pokemon(27,
             "sandshrew",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/27.png",
             regions.get(0), listOf(pokemonTypeMock[5])
         ),
         Pokemon(29,
             "nidoran",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/29.png",
             regions.get(0), listOf(pokemonTypeMock[17])
         ),
         Pokemon(35,
             "clefairy",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/35.png",
             regions.get(0), listOf(pokemonTypeMock[10])
         ),
         Pokemon(37,
             "vulpix",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/37.png",
             regions.get(0), listOf(pokemonTypeMock[1])
         ),
         Pokemon(39,
             "jigglypuff",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/39.png",
             regions.get(0), listOf(pokemonTypeMock[13], pokemonTypeMock[10])
         ),
         Pokemon(41,
             "zubat",
             "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                     "/sprites/pokemon/other/official-artwork/41.png",
             regions.get(0), listOf(pokemonTypeMock[16], pokemonTypeMock[15])
         ),
        Pokemon(152,
            "Chikorita",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/152.png",
            regions.get(1), listOf(pokemonTypeMock[4])
        ),
        Pokemon(155,
            "Cyndaquil",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/155.png",
            regions.get(1), listOf(pokemonTypeMock[1])
        ),
        Pokemon(158,
            "Totodile",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/158.png",
            regions.get(1), listOf(pokemonTypeMock[0])
        ),
        Pokemon(252,
            "Treecko",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/252.png",
            regions.get(2), listOf(pokemonTypeMock[4])
        ),
        Pokemon(255,
            "Torchic",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/255.png",
            regions.get(2), listOf(pokemonTypeMock[1])
        ),
        Pokemon(258,
            "Mudkip",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/258.png",
            regions.get(2), listOf(pokemonTypeMock[0])
        ),
        Pokemon(387,
            "Turtwig",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/387.png",
            regions.get(3), listOf(pokemonTypeMock[4])
        ),
        Pokemon(390,
            "Chimchar",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/390.png",
            regions.get(3), listOf(pokemonTypeMock[1])
        ),
        Pokemon(393,
            "Piplup",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/393.png",
            regions.get(3), listOf(pokemonTypeMock[0])
        ),
         )

    var pokemonDetail = listOf(
        PokemonDetail(
            1,
            "bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${1}.png",
            pokemonDetailDescription,
            listOf(pokemonTypeMock[2], pokemonTypeMock[16]),
            47.06f, 0.69f,0.69f, 0.69f)
    )

    /*  var pokemonDetail = pokemons.map {
          PokemonDetail(
              it,
              pokemonDetailDescription,
              pokemonTypeMock.asSequence().shuffled().take(1).toList(),
              ( Random.nextDouble(20.0,50.0) * 100.0).roundToInt() / 100.0,
              (Random.nextDouble(0.20, 2.0) * 100.0).roundToInt() / 100.0,
              PokemonStats(),
              generateSequence {
                  PokemonEvolution(1, pokemons.random(), false,
                      0,"", 0, "")
              }.take(Random.nextInt(1,3)).toList()
          )
      }*/
}