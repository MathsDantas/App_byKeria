package com.example.bykeria
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bykeria.ui.pages.CadastroScreen
import com.example.bykeria.ui.pages.HomeScreen
import com.example.bykeria.ui.pages.LoginScreen
import com.example.bykeria.ui.theme.YourAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Crie o NavController
            val navController = rememberNavController()

            // Defina a navegação com o NavHost
            YourAppTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home_screen") {
                        composable("home_screen") {
                            HomeScreen(navController = navController)
                        }
                        composable("login_screen") {
                            LoginScreen()
                        }
                        composable("cadastro_screen") {
                            CadastroScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}