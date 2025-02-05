package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout

@Composable
fun FaqScreen(navController: NavController) {
    MainScreenLayout(navController = navController) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagem de fundo
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            FAQ(navController, paddingValues)
        }
    }
}

@Composable
fun FAQ(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "FAQ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }
            item { FaqItem("Posso alugar mais de uma bike por vez?", "Sim, você pode alugar até 2 bikes por vez!") }
            item { FaqItem("Tenho que devolver no posto que peguei?", "Não, você pode devolver a bike em qualquer um de nossos postos!") }
            item { FaqItem("Qual o tempo limite para ficar com a bike?", "Recomendamos que você não passe mais de 3 dias com a bike") }
        }

        // Texto antes da barra de pesquisa
        Text(
            text = "Não viu sua dúvida aqui? Nos escreva e vamos te responder!",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Barra de pesquisa
        val searchQuery = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(Color(0xFFFFEB3B))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (searchQuery.value.isEmpty()) {
                Text(
                    text = "Digite sua dúvida aqui",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            BasicTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
    }
}

@Composable
fun FaqItem(question: String, answer: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = answer,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}
