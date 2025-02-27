package com.example.bykeria.ui.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.SettingsViewModel
import com.example.bykeria.ui.components.SettingsViewModelFactory
import com.example.bykeria.ui.theme.YourAppTheme


@Composable
fun SettingsScreen(navController: NavHostController) {

    val context = LocalContext.current


    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )


    val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = false)


    val isAutoTheme by viewModel.isAutoTheme.collectAsState(initial = false)


    val currentTheme = if (isAutoTheme) {
        viewModel.isNightTime()
    } else {
        isDarkTheme
    }

    MainScreenLayout(navController = navController) { paddingValues ->
        YourAppTheme(darkTheme = currentTheme) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )


                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(16.dp)
                        ) {

                            Text(
                                text = "Modo Automático",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(8.dp)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isAutoTheme) "Ativado" else "Desativado",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Switch(
                                    checked = isAutoTheme,
                                    onCheckedChange = {
                                        viewModel.setAutoTheme(it)
                                        if (it) {

                                            viewModel.setDarkTheme(viewModel.isNightTime())
                                        }
                                    },
                                    enabled = true
                                )
                            }

                            Spacer(modifier = Modifier.padding(16.dp))


                            Text(
                                text = "Modo de Cor",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(8.dp)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isDarkTheme) "Escuro" else "Claro",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Switch(
                                    checked = isDarkTheme,
                                    onCheckedChange = {
                                        viewModel.setDarkTheme(it)
                                        if (it || !it) {

                                            viewModel.setAutoTheme(false)
                                        }
                                    },
                                    enabled = !isAutoTheme
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}


