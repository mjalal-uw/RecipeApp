package com.example.recipeapp.ui.screens

sealed class Screen(val route: String) {

    object RecipeScreen : Screen("recipescreen")
    object CategoryDetailScreen : Screen("categorydetailscreen")
}