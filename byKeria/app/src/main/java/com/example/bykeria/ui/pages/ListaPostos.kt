package com.example.bykeria.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bykeria.R
import com.example.bykeria.ui.components.MainScreenLayout
import com.example.bykeria.ui.components.PostoCard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bykeria.ui.components.SettingsDataStore
import com.example.bykeria.viewmodel.PostosViewModel
import com.example.bykeria.viewmodel.PostosViewModelFactory

@Composable
fun PostosScreen(
    navController: NavController,
    settingsDataStore: SettingsDataStore
) {

    val viewModel: PostosViewModel = viewModel(
        factory = PostosViewModelFactory(settingsDataStore)
    )

    val postos by viewModel.postos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPostos()
    }

    MainScreenLayout(navController = navController) { paddingValues ->
        Box(modifier = Modifier.padding(top = 24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(postos) { posto ->
                        PostoCard(posto = posto, navController = navController)
                    }
                }
            }
        }
    }
}

