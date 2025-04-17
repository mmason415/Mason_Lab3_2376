package com.example.mmason_lab3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mmason_lab3.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel() {

    private val _selectedRecipeId = MutableStateFlow<Int?>(null)
    val selectedRecipeId: StateFlow<Int?> = _selectedRecipeId

    val recipes = listOf(
        Recipe(1, "Spaghetti Bolognese", "A classic Italian dish with rich meat sauce."),
        Recipe(2, "Chicken Curry", "A spicy and savory curry with tender chicken pieces."),
        Recipe(3, "Beef Stroganoff", "A creamy dish with sautéed pieces of beef and mushrooms.")
    )

    fun selectRecipe(id: Int) {
        _selectedRecipeId.value = id
    }

    fun getRecipeById(id: Int?): Recipe? {
        return recipes.find { it.id == id }
    }
}
