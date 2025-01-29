package com.example.bykeria.ui.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bykeria.ui.mocks.bikesAduList
import com.example.bykeria.ui.mocks.bikesKidList
import com.example.bykeria.ui.pages.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("login_screen") { LoginScreen(navController) }
        composable("cadastro_screen") { CadastroScreen(navController) }
        composable("lista_postos") { PostosScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("bikes") { BikesScreen(navController) }
        composable("detalhes_postos/{postoId}",
            arguments = listOf(navArgument("postoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postoId = backStackEntry.arguments?.getInt("postoId")
            DetalhesPostoScreen(postoId,navController)
        }
        composable("detalhe_bikeskid") { BikesKidDetailsScreen(navController, bikes = bikesKidList) }
        composable("detalhe_bikesadu") { BikesAduDetailsScreen(navController, bikes = bikesAduList) }
    }
}
