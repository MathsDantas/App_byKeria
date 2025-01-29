package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.mocks.Postos
import com.example.bykeria.ui.mocks.postosList

@Composable
fun DetalhesPostoScreen(postoId: Int?, navController: NavController) {
    MainScreenLayout(navController = navController) { paddingValues ->
        // Simula buscar os dados com base no ID
        val posto = getPostoById(postoId)
        // Função para buscar os dados do posto
        if (posto != null) {
            Image(
                painter = painterResource(id = R.drawable.cadastro), // Substitua pelo nome da sua imagem
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ajusta a imagem para cobrir a tela
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Imagem do posto
                Image(
                    painter = painterResource(id = posto.imageRes),
                    contentDescription = "Imagem do posto",
                    modifier = Modifier
                        .size(500.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                // Nome da unidade
                Text(
                    text = posto.unidade,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.padding(4.dp))
                // Endereço
                Text(
                    text = posto.endereco,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(16.dp))
                // Quantidade de bikes
                Text(
                    text = "Quantidade de bikes: ${posto.quantidadeBikes}",
                    style = MaterialTheme.typography.bodyLarge
                )
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

    // Função simulada para buscar os dados de um posto
    fun getPostoById(id: Int?): Postos? {
        return postosList.find { it.id == id } // Retorna o posto correspondente
    }


