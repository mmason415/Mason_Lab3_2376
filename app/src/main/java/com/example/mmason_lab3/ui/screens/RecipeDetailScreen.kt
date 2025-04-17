package com.example.mmason_lab3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mmason_lab3.model.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        recipe?.let {
            Text(it.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(it.description, style = MaterialTheme.typography.bodyMedium)
        } ?: Text("Please select a recipe", style = MaterialTheme.typography.bodyMedium)
    }
}
