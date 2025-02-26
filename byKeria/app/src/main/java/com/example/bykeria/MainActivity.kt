package com.example.bykeria

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bykeria.notifications.NotificationHelper
import com.example.bykeria.notifications.NotificationScheduler
import com.example.bykeria.ui.components.AppNavigation
import com.example.bykeria.ui.components.SettingsDataStore
import com.example.bykeria.ui.components.SettingsViewModel
import com.example.bykeria.ui.components.SettingsViewModelFactory
import com.example.bykeria.ui.theme.YourAppTheme

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bykeria.viewmodel.LoginViewModel
import com.example.bykeria.viewmodel.LoginViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criar canal de notificações
        NotificationHelper.createNotificationChannel(this)

        // Agendar notificações dos postos
        NotificationScheduler.schedulePostoNotifications(this)

        // Solicitar permissão para notificações (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }

        setContent {
            val context = LocalContext.current
            val settingsDataStore = SettingsDataStore(context)
            AppContent(settingsDataStore) // Chamando o conteúdo da aplicação
        }
    }
}

@Composable
fun AppContent(settingsDataStore: SettingsDataStore) {
    val context = LocalContext.current
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context) // Passa o contexto para a Factory
    )

    // Forneça um valor inicial para isDarkTheme
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState(initial = false)

    YourAppTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Log para garantir que o LoginViewModel está sendo criado corretamente
            val LoginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(settingsDataStore)
            )
            Log.d("AppContent", "LoginViewModel criado com sucesso")

            AppNavigation(navController, settingsDataStore) // Passar o SettingsDataStore
        }
    }
}



