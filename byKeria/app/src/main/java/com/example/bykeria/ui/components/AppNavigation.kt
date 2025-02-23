package com.example.bykeria.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bykeria.ui.mocks.bikesAduList
import com.example.bykeria.ui.mocks.bikesKidList
import com.example.bykeria.ui.pages.*

@Composable
fun AppNavigation(navController: NavHostController, settingsDataStore: SettingsDataStore) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("login_screen") { LoginScreen(navController, settingsDataStore) }
        composable("cadastro_screen") { CadastroScreen(navController) }
        composable("faq_screen") { FaqScreen(navController) }
        composable("lista_postos") { PostosScreen(navController, settingsDataStore) }
        composable("settings") { SettingsScreen(navController) }
        composable("bikes") { BikesScreen(navController) }
        composable("detalhes_posto/{postoId}") { backStackEntry ->
            val postoId = backStackEntry.arguments?.getString("postoId")?.toIntOrNull()
            DetalhesPostoScreen(
                postoId = postoId,
                navController = navController,
                settingsDataStore = settingsDataStore
            )
        }
        composable("detalhe_bikeskid") { BikesKidDetailsScreen(navController, bikes = bikesKidList) }
        composable("detalhe_bikesadu") { BikesAduDetailsScreen(navController, bikes = bikesAduList) }
    }
}
