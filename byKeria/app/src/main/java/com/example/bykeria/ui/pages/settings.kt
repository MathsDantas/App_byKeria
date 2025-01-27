package com.example.bykeria.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.theme.YourAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    MainScreenLayout(navController = navController) { paddingValues ->
        val settingsViewModel: SettingsViewModel = viewModel()
        val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Configurações") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Modo de Cor",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(text = if (isDarkTheme) "Escuro" else "Claro")
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { settingsViewModel.toggleTheme() } // Atualiza o estado
                        )
                    }
                }
            }
        )
    }
}