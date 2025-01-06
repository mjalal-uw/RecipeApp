package com.example.recipeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.data.Category
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.ui.viewmodels.MainViewModel

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState: MainViewModel.RecipeState,
    navigateToCategoryDetailScreen: (Category) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "ERROR OCCURRED")
            }

            else -> {
                CategoryScreen(
                    categories = viewState.categoriesList, navigateToCategoryDetailScreen
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToCategoryDetailScreen: (Category) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category, navigateToCategoryDetailScreen)
        }
    }
}

@Composable
fun CategoryItem(
    category: Category, navigateToCategoryDetailScreen: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { navigateToCategoryDetailScreen(category) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)

        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}

@Preview
@Composable
fun PreviewRecipeScreen() {
    RecipeAppTheme {
        RecipeScreen(viewState = MainViewModel.RecipeState(), navigateToCategoryDetailScreen = {})
    }
}