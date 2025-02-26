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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.data.model.CadastroViewModel
import com.example.bykeria.ui.theme.YourAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CadastroScreen(navController: NavController) {
    val viewModel: CadastroViewModel = viewModel()

    var nome by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun onCadastrarClicked() {
        if (senha != confirmarSenha) {
            scope.launch {
                snackbarHostState.showSnackbar("As senhas não coincidem", duration = SnackbarDuration.Short)
            }
        } else {
            isLoading = true
            viewModel.cadastrar(
                name = nome,
                username = username,
                email = email,
                password = senha,
                onSuccess = {
                    scope.launch {
                        val job = launch {
                            snackbarHostState.showSnackbar("Conta criada com sucesso!", withDismissAction = false)
                        }
                        delay(2000L)
                        job.cancel()
                        navController.navigate("home_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                },

                onError = { errorMessage ->
                    isLoading = false
                    scope.launch {
                        val message = errorMessage.ifBlank { "Erro ao criar conta" }
                        snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
                    }
                }
            )
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.cadastro),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Cadastre-se!", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(32.dp))

                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = confirmarSenha,
                    onValueChange = { confirmarSenha = it },
                    label = { Text("Confirmar Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(
                        onClick = { onCadastrarClicked() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading
                    ) {
                        Text(text = "Criar Conta", fontSize = 18.sp, color = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Já tem uma conta? ", fontSize = 16.sp)
                    Text(
                        text = "Fazer Login",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable(enabled = !isLoading) {
                            navController.navigate("login_screen") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            )
        }
    }
}




