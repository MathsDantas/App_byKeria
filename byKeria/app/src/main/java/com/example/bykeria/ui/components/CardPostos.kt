package com.example.bykeria.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.ui.mocks.Postos

@Composable
fun PostoCard(posto: Postos, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground // Cor de fundo do Card
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagem do posto
            Image(
                painter = painterResource(id = posto.imageRes),
                contentDescription = "Imagem do posto ${posto.unidade}",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )
            // Nome da unidade
            Text(
                text = posto.unidade,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 37.sp
            )
            // Endereço
            Text(
                text = posto.endereco,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 23.sp
            )
            Button(
                onClick = {
                    Log.d("Navigation", "Navigating to detalhesPostos/${posto.id}")
                    navController.navigate("detalhesPosto/${posto.id}")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.width(250.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(text = "Mais Informações", fontSize = 20.sp)
            }

        }
    }
}
