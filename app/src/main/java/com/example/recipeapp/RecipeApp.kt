package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.data.Category
import com.example.recipeapp.ui.screens.CategoryDetailScreen
import com.example.recipeapp.ui.screens.RecipeScreen
import com.example.recipeapp.ui.screens.Screen
import com.example.recipeapp.ui.viewmodels.MainViewModel

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {

        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToCategoryDetailScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Screen.CategoryDetailScreen)
            })
        }

        composable(route = Screen.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")

            CategoryDetailScreen(category = category)
        }
    }
}