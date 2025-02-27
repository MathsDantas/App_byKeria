package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.SettingsDataStore
import com.example.bykeria.viewmodel.LoginViewModel
import com.example.bykeria.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    settingsDataStore: SettingsDataStore
) {

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(settingsDataStore)
    )

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var loginError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.login),
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
                Text(
                    text = "Faça seu Login!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Text(text = "Username", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    placeholder = { Text("Digite seu username") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Senha", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    placeholder = { Text("Digite sua senha") },
                    visualTransformation = PasswordVisualTransformation()
                )

                if (loginError) {
                    Text(text = "Erro ao fazer login. Tente novamente.", color = Color.Red, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(
                        onClick = {
                            isLoading = true
                            loginError = false

                            viewModel.login(email.text, password.text) { success ->
                                isLoading = false
                                if (success) {
                                    navController.navigate("lista_postos") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                } else {
                                    loginError = true
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Usuário ou senha incorretos!",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                    ) {
                        Text(text = "Acessar", fontSize = 18.sp)
                    }
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


