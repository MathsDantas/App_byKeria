package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.PostoCard
import com.example.bykeria.ui.mocks.postosList

@Composable
fun PostosScreen(navController: NavController) {
    MainScreenLayout(navController = navController) { paddingValues ->
        Box(modifier = Modifier.padding(top = 24.dp)) {
            // Imagem de fundo
            Image(
                painter = painterResource(id = R.drawable.login), // Substitua pelo nome da sua imagem
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ajusta a imagem para cobrir a tela
                modifier = Modifier.fillMaxSize()
            )
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
            ) {
                items(postosList) { posto ->
                    PostoCard(posto = posto, navController = navController)
                }
            }
        }
    }
}

