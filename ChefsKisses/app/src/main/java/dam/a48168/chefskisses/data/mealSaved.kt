package dam.a48168.chefskisses.data

data class mealSaved(
    var idMeal: Int = 0,
    var mail: String = ""
)
{
    constructor() : this(0,"")
}