package com.example.mobdev.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobdev.mvvm.MainViewModel


@Composable
fun Recipe(modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                Text("Progress", modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text("ERROR OCCURRED", modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}