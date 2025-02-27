package com.example.bykeria.ui.pages


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.ui.components.BikesAduListItem
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.mocks.BikesAdu


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikesAduDetailsScreen(
    navController: NavController,
    bikes: List<BikesAdu>
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredBikes = bikes.filter { bike ->
        listOf(
            bike.modelo,
            bike.tipo,
            bike.descricao
        ).any { it.contains(searchQuery, ignoreCase = true) }
    }

    MainScreenLayout(navController = navController) { paddingValues ->
        Scaffold(
            topBar = {
                SearchBar(searchQuery) { newQuery ->
                    searchQuery = newQuery
                }
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(filteredBikes) { bike ->
                        BikesAduListItem(bike = bike)
                    }
                }
            }
        )
    }
}

@Composable
fun SearchBar(searchQuery: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        placeholder = { Text("Pesquisar bicicletas...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisar"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}

