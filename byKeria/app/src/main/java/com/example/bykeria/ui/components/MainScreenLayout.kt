package com.example.bykeria.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenLayout(navController: NavController, content: @Composable (PaddingValues) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = androidx.compose.ui.Alignment.Center // Centraliza verticalmente
                    ) {
                        Text(
                            text = "byKeria",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    }
                },
                modifier = Modifier.height(40.dp), // Define uma altura menor
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                navController.navigate("settings")
                            },
                            text = { Text("Configurações") }
                        )
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                navController.navigate("sobre")
                            },
                            text = { Text("Sobre") }
                        )
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                navController.navigate("FAQ")
                            },
                            text = { Text("FAQ") }
                        )
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                navController.navigate("home_screen")
                            },
                            text = { Text("Sair") }
                        )
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(40.dp), // Ajusta a altura da barra para 40.dp
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                NavigationBarItem(
                    onClick = {
                        navController.navigate("settings")
                    },
                    selected = false,  // Adicionando selected false
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Define a cor do ícone diretamente
                        )
                    }
                )

                NavigationBarItem(
                    onClick = {
                        navController.navigate("lista_postos")
                    },
                    selected = false,  // Adicionando selected false
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Home",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Define a cor do ícone diretamente
                        )
                    }
                )

                NavigationBarItem(
                    onClick = {
                        navController.navigate("bikes")
                    },
                    selected = false,  // Adicionando selected false
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.bike2),
                            contentDescription = "Other",
                            modifier = Modifier.size(35.dp),
                            tint = Color.Black // Define a cor do ícone diretamente
                        )
                    }
                )
            }
        },

        content = { paddingValues -> content(paddingValues) }
    )
}
