package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.theme.YourAppTheme

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginClicked: (String, String) -> Unit = { _, _ -> },
    onForgotPasswordClicked: () -> Unit = {}
)  {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) { Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.login), // Substitua pelo nome da sua imagem
            contentDescription = null,
            contentScale = ContentScale.Crop, // Ajusta a imagem para cobrir a tela
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            // Mensagem de título
            Text(
                text = "Faça seu Login!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo de texto para email
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Digite seu email") }
            )

            // Campo de texto para senha
            Text(
                text = "Senha",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Digite sua senha") },
                visualTransformation = PasswordVisualTransformation() // Oculta a senha
            )

            // Link "Esqueci minha senha"
            ClickableText(
                text = AnnotatedString("Esqueci minha senha"),
                onClick = { onForgotPasswordClicked() },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            // Botão de login
            Button(
                onClick = { onLoginClicked(email.text, password.text); navController.navigate("lista_postos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Cor de fundo do botão
                    contentColor = Color.Black // Cor do texto
                )

            ) {
                Text(text = "Acessar", fontSize = 18.sp)

            }
        }
    }
} }