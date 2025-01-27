package com.example.bykeria.ui.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.R

@Composable
fun HomeScreen(navController: NavController) {

    Image(
        painter = painterResource(id = R.drawable.homebg), // Substitua pelo nome da sua imagem
        contentDescription = null,
        contentScale = ContentScale.Crop, // Ajusta a imagem para cobrir a tela
        modifier = Modifier.fillMaxSize()
    )
    // Tela de boas-vindas com os botões
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo ao byKeria!",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { navController.navigate("login_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }

        Button(
            onClick = { navController.navigate("cadastro_screen") },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Cadastre-se", fontSize = 18.sp)
        }
    }
}