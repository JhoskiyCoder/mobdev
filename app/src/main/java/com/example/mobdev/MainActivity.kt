package com.example.mobdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobdev.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen { name, age ->
                navController.navigate("second_screen/$name/$age")
            }
        }
        composable("second_screen/{name}/{age}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0
            SecondScreen(name, age) {
                navController.navigate("third_screen")
            }
        }
        composable("third_screen") {
            ThirdScreen {
                navController.popBackStack()
            }
        }
    }
}
