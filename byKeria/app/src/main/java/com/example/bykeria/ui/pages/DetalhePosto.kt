package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.SettingsDataStore
import com.example.bykeria.ui.components.SettingsViewModel
import com.example.bykeria.ui.mocks.Postos
import com.example.bykeria.ui.mocks.postosList
import kotlinx.coroutines.launch

@Composable
fun DetalhesPostoScreen(
    postoId: Int?,
    navController: NavController,
    settingsDataStore: SettingsDataStore // Agora usa SettingsDataStore corretamente
) {
    val coroutineScope = rememberCoroutineScope()
    val favorites by settingsDataStore.favoritePostos.collectAsState(initial = emptySet())
    val isFavorite = postoId in favorites

    MainScreenLayout(navController = navController) { paddingValues ->
        val posto = getPostoById(postoId)

        if (posto != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = posto.imageRes),
                    contentDescription = "Imagem do posto",
                    modifier = Modifier.size(500.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = posto.unidade,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = posto.endereco,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = "Quantidade de bikes: ${posto.quantidadeBikes}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(modifier = Modifier.padding(16.dp))

                // Botão de Favorito
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
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
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

fun getPostoById(id: Int?): Postos? {
    return postosList.find { it.id == id } // Retorna o posto correspondente
}
