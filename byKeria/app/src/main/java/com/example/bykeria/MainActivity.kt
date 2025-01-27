package com.example.bykeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bykeria.ui.components.AppNavigation
import com.example.bykeria.ui.theme.YourAppTheme
import com.example.bykeria.ui.pages.SettingsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent() // Chamando o conteúdo da aplicação
        }
    }
}

@Composable
fun AppContent() {
    val settingsViewModel: SettingsViewModel = viewModel()
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

    YourAppTheme(darkTheme = isDarkTheme) { // Passando o tema para o YourAppTheme
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(navController) // Navegação
        }
    }
}
