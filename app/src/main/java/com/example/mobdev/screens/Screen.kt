package com.example.mobdev.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FirstScreen(navigateToSecondScreen: (String, Int) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the First Screen", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Enter your age") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val ageValue = age.text.toIntOrNull() ?: 0
                navigateToSecondScreen(name.text, ageValue)
            }
        ) {
            Text("Go to Second Screen")
        }
    }
}

@Preview
@Composable
fun FirstScreenPreview() {
    FirstScreen { _, _ -> }
}
