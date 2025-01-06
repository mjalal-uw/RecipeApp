package com.example.recipeapp.ui.screens

import com.example.recipeapp.data.Category
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object RecipeScreen : Screen()
    @Serializable
    data class CategoryDetailScreen(val category: Category) : Screen()
}