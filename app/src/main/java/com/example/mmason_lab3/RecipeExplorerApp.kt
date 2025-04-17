package com.example.mmason_lab3

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.mmason_lab3.ui.screens.RecipeListScreen
import com.example.mmason_lab3.ui.screens.RecipeDetailScreen
import com.example.mmason_lab3.viewmodel.RecipeViewModel
import androidx.navigation.navArgument


@Composable
fun RecipeExplorerApp(viewModel: RecipeViewModel = viewModel()) {
    val selectedRecipeId by viewModel.selectedRecipeId.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    if (screenWidth >= 600) {
        // Tablet layout
        Row(Modifier.fillMaxSize()) {
            RecipeListScreen(
                recipes = viewModel.recipes,
                onRecipeClick = viewModel::selectRecipe,
                modifier = Modifier.weight(1f)
            )
            RecipeDetailScreen(
                recipe = viewModel.getRecipeById(selectedRecipeId),
                modifier = Modifier.weight(2f)
            )
        }
    } else {
        // Phone layout with navigation
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                RecipeListScreen(
                    recipes = viewModel.recipes,
                    onRecipeClick = {
                        viewModel.selectRecipe(it)
                        navController.navigate("detail/$it")
                    }
                )
            }
            composable(
                "detail/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("recipeId")
                RecipeDetailScreen(recipe = viewModel.getRecipeById(id))
            }
        }
    }
}
