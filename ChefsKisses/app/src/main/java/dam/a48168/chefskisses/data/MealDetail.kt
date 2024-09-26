package dam.a48168.chefskisses.data


data class MealDetail(
    val mealID: Int = 0,
    val email: String = "",
    val name: String = "",
    val type: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val description: String = "",
    val imageUrl: String? = ""
){
    constructor() : this(0,"","","", emptyList(), emptyList(),"","")
}