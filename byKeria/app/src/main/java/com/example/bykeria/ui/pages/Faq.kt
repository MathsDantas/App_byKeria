package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout

@Composable
fun FaqScreen (navController: NavController) {
    MainScreenLayout(navController = navController) { paddingValues ->
        Box(modifier = Modifier.padding(top = 24.dp)) {
            // Imagem de fundo
            Image(
                painter = painterResource(id = R.drawable.login), // Substitua pelo nome da sua imagem
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ajusta a imagem para cobrir a tela
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}