package dam.a48168.chefskisses.model

import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.IngredientType

object MockData{

    var type = listOf<IngredientType>(
        IngredientType(0,"Meat", R.drawable.bg_meat),
        IngredientType(1,"Fish",R.drawable.bg_fish),
        IngredientType(2,"Vegetarian",R.drawable.bg_veg)
    )

}