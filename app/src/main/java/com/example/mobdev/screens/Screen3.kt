package com.example.mobdev.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ThirdScreen(navigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Third Screen", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = navigateBack) {
            Text("Go Back")
        }
    }
}

@Preview
@Composable
fun ThirdScreenPreview() {
    ThirdScreen {}
}
