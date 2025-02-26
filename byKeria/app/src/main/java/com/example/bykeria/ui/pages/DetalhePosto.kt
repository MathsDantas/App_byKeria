package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.data.model.Postos
import com.example.bykeria.data.network.RetrofitInstance
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.PostoMap
import com.example.bykeria.ui.components.SettingsDataStore
import com.example.bykeria.ui.mocks.getMockCoordinates
import com.example.bykeria.ui.mocks.postosList
import com.example.bykeria.viewmodel.DetalhesPostoViewModel
import com.example.bykeria.viewmodel.DetalhesPostoViewModelFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@Composable
fun DetalhesPostoScreen(
    postoId: Int?,
    navController: NavController,
    settingsDataStore: SettingsDataStore
) {
    val authService = remember { RetrofitInstance.api }
    val viewModel: DetalhesPostoViewModel = viewModel(
        factory = DetalhesPostoViewModelFactory(authService, settingsDataStore)
    )

    val posto by viewModel.posto.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(postoId) {
        postoId?.let { viewModel.fetchPostoDetalhes(it) }
    }

    // Obter coordenadas mockadas com base no ID do posto
    val postoCoordinates = postoId?.let { getMockCoordinates(it) } ?: LatLng(0.0, 0.0)

    MainScreenLayout(navController = navController) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Text(
                text = error!!,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        } else if (posto != null) {
            Image(
                painter = painterResource(id = R.drawable.homebg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagem do posto (proporção original)
                Image(
                    painter = painterResource(R.drawable.posto2),
                    contentDescription = "Imagem do posto",
                    modifier = Modifier
                        .fillMaxWidth() // Ocupa a largura máxima disponível
                        .aspectRatio(344f / 130f) // Mantém a proporção original (344x130)
                        .padding(vertical = 8.dp) // Espaçamento vertical menor
                )

                Spacer(modifier = Modifier.padding(4.dp)) // Espaçamento menor

                Text(
                    text = posto!!.nameUnidade,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 30.sp // Aumentei o tamanho da fonte
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp)) // Espaçamento menor

                Text(
                    text = posto!!.endereco,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 24.sp // Aumentei o tamanho da fonte
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp)) // Espaçamento menor

                // Quantidade de bikes adultas
                val bikesAdultas = posto!!.bikes.count { it.type == "adulto" }
                Text(
                    text = "Quantidade de bikes Adultas: $bikesAdultas",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 24.sp // Aumentei o tamanho da fonte
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp)) // Espaçamento menor

                // Quantidade de bikes infantis
                val bikesInfantis = posto!!.bikes.count { it.type == "infantil" }
                Text(
                    text = "Quantidade de bikes Infantis: $bikesInfantis",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 24.sp // Aumentei o tamanho da fonte
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp)) // Espaçamento menor

                // Botão de Favorito
                val coroutineScope = rememberCoroutineScope()
                val favorites by settingsDataStore.favoritePostos.collectAsState(initial = emptySet())
                val isFavorite = postoId in favorites

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            postoId?.let { settingsDataStore.toggleFavoritePosto(it) }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favoritar Posto",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(48.dp) // Aumentei o tamanho do ícone
                    )
                }

                // Mapa do posto com coordenadas mockadas
                PostoMap(latitude = postoCoordinates.latitude, longitude = postoCoordinates.longitude)
            }
        } else {
            Text(
                text = "Posto não encontrado!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

