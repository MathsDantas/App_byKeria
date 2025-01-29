package com.example.bykeria.ui.pages


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bykeria.ui.components.BikesAduListItem
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.mocks.BikesAdu


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikesAduDetailsScreen(
    navController: NavController,
    bikes: List<BikesAdu>, // Lista de bicicletas
) {
    MainScreenLayout(navController = navController) { paddingValues ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Lista de Bicicletas",
                            style = MaterialTheme.typography.titleLarge // Estilo do Material3
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(bikes) { bike ->
                        BikesAduListItem(
                            bike = bike,
                        )
                    }
                }
            }
        )
    } }