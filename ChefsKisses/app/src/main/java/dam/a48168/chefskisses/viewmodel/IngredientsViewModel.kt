package dam.a48168.chefskisses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dam.a48168.chefskisses.data.IngredientType

class IngredientsViewModel: ViewModel() {

    private val _types = MutableLiveData<List<IngredientType>?>()
    val types: LiveData<List<IngredientType>?>
        get() = _types



}