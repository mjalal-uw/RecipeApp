package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.CustomNavType
import com.example.recipeapp.data.Category
import com.example.recipeapp.ui.screens.CategoryDetailScreen
import com.example.recipeapp.ui.screens.RecipeScreen
import com.example.recipeapp.ui.screens.Screen
import com.example.recipeapp.ui.viewmodels.MainViewModel
import kotlin.reflect.typeOf

@Composable
fun RecipeApp(navController: NavHostController, modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen) {

        composable<Screen.RecipeScreen> {
            RecipeScreen(viewState = viewState, navigateToCategoryDetailScreen = { category ->
                navController.navigate(Screen.CategoryDetailScreen(category))
            })
        }

        composable<Screen.CategoryDetailScreen>(
            typeMap = mapOf(
                typeOf<Category>() to CustomNavType.CategoryType
            )
        ) {
            val arguments = it.toRoute<Screen.CategoryDetailScreen>()
            CategoryDetailScreen(category = arguments.category)
        }
    }
}