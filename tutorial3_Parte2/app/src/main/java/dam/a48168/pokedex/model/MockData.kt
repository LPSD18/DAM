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
        PokemonRegion(1, "Kanto"),
        PokemonRegion(2, "Johto"),
        PokemonRegion(3, "Hoenn"),
        PokemonRegion(4, "Sinnoh"),
        PokemonRegion(5, "Unova"),
        PokemonRegion(6, "Kalos"),
        PokemonRegion(7, "Alola"),
        PokemonRegion(8, "Galar"),
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
                    "/sprites/pokemon/other/official-artwork/1.png",
            0)

        ,
        Pokemon(4,
            "charmander",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/4.png",
            0
        ),
        Pokemon(6,
            "squirtle",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/7.png",
            0
        ),
        Pokemon(10,
            "caterpie",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/10.png",
            0
        ),
        Pokemon(13,
            "weedle",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/13.png",
            0
        ),
        Pokemon(16,
            "pidgey",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/16.png",
            0
        ),
        Pokemon(19,
            "rattata",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/19.png",
            0
        ),
        Pokemon(21,
            "spearow",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/21.png",
            0
        ),
        Pokemon(23,
            "ekans",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/23.png",
            0
        ),
        Pokemon(25,
            "pikachu",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/25.png",
            0
        ),
        Pokemon(27,
            "sandshrew",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/27.png",
            0
        ),
        Pokemon(29,
            "nidoran",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/29.png",
            0
        ),
        Pokemon(35,
            "clefairy",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/35.png",
            0
        ),
        Pokemon(37,
            "vulpix",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/37.png",
            0
        ),
        Pokemon(39,
            "jigglypuff",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/39.png",
            0
        ),
        Pokemon(41,
            "zubat",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/41.png",
            0
        ),
        Pokemon(152,
            "Chikorita",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/152.png",
            1
        ),
        Pokemon(155,
            "Cyndaquil",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/155.png",
            1
        ),
        Pokemon(158,
            "Totodile",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/158.png",
            1
        ),
        Pokemon(252,
            "Treecko",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/252.png",
            2
        ),
        Pokemon(255,
            "Torchic",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/255.png",
            2
        ),
        Pokemon(258,
            "Mudkip",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/258.png",
            2
        ),
        Pokemon(387,
            "Turtwig",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/387.png",
            3
        ),
        Pokemon(390,
            "Chimchar",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/390.png",
            3
        ),
        Pokemon(393,
            "Piplup",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/393.png",
            3
        ),
    )

    var pokemonDetail = listOf(
        PokemonDetail(1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/1.png",
            "For some time after its birth, it uses the nutrients that are packed into the seed on its back in order to grow.",
            listOf(pokemonTypeMock[4], pokemonTypeMock[16]),
            6.89f,0.7f,"Overgrow",1,45),
        PokemonDetail(4,
            "Charmander",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master" +
                    "/sprites/pokemon/other/official-artwork/4.png",
            "The flame on its tail shows the strength of its life-force. If Charmander is weak, the flame also burns weakly.",
            listOf(pokemonTypeMock[1]),
            8.5f,0.6f,"Blaze",1,39)
    )

    val typeColorMap = mapOf(
        "flying" to R.color.flying,
        "poison" to R.color.poison,
        "ground" to R.color.ground,
        "fire" to R.color.fire,
        "ghost" to R.color.ghost,
        "ice" to R.color.ice,
        "psychic" to R.color.psychic,
        "rock" to R.color.rock,
        "steel" to R.color.steel,
        "water" to R.color.water,
        "grass" to R.color.grass,
        "normal" to R.color.normal,
        "bug" to R.color.bug,
        "dark" to R.color.dark,
        "dragon" to R.color.dragon,
        "fighting" to R.color.fighting,
        "electric" to R.color.electric,
        "fairy" to R.color.fairy
    )
}