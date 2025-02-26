package com.example.bykeria.ui.pages

import android.content.Context
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.SettingsViewModel
import com.example.bykeria.ui.components.SettingsViewModelFactory
import com.example.bykeria.ui.theme.YourAppTheme


@Composable
fun SettingsScreen(navController: NavHostController) {
    // Obter o contexto
    val context = LocalContext.current

    // Criar o ViewModel usando o contexto
    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )

    // Obter o estado do tema escuro
    val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = false)

    // Obter o estado do modo automático
    val isAutoTheme by viewModel.isAutoTheme.collectAsState(initial = false)

    // Definir o tema com base no modo automático ou manual
    val currentTheme = if (isAutoTheme) {
        viewModel.isNightTime() // Verifica o horário do sistema
    } else {
        isDarkTheme // Usa o tema manual
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
                        // A imagem de fundo
                        Image(
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Conteúdo da tela
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(16.dp)
                        ) {
                            // Switch para o modo automático
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
                                            // Desabilita o switch manual quando o modo automático é ativado
                                            viewModel.setDarkTheme(viewModel.isNightTime())
                                        }
                                    },
                                    enabled = true // O switch do modo automático nunca deve ser desabilitado
                                )
                            }

                            Spacer(modifier = Modifier.padding(16.dp))

                            // Switch para o tema escuro/claro manual
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


