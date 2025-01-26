package com.example.bykeria.ui.pages


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.theme.YourAppTheme

@Composable
fun CadastroScreen(navController: NavController) {
    // Variáveis para os campos de entrada
    var nome by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var isDarkTheme by remember { mutableStateOf(false) }

    // Função para validação e cadastro
    val context = LocalContext.current

    // Exibindo Toast dentro de uma função composable
    fun onCadastrarClicked() {
        if (senha != confirmarSenha) {
            Toast.makeText(context, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
        } else {
            // Adicionar a lógica de cadastro aqui
            // Exemplo: enviar os dados para a API ou salvar localmente
            Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    YourAppTheme(darkTheme = isDarkTheme) {
        Surface(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.detalheposto), // Substitua pelo nome da sua imagem
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

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it }
                )

                Text(text = "Cadastre-se!", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(32.dp))

                // Campo Nome
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Username
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Email
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Senha
                TextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Confirmar Senha
                TextField(
                    value = confirmarSenha,
                    onValueChange = { confirmarSenha = it },
                    label = { Text("Confirmar Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botão Criar Conta
                Button(
                    onClick = { onCadastrarClicked() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Criar Conta", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Link para fazer login
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Já tem uma conta? ", fontSize = 16.sp)
                    Text(
                        text = "Fazer Login",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate("login_screen") // Substitua pelo nome correto da rota de login
                        }
                    )
                }
            }
        }
    }
}
