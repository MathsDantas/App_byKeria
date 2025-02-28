package com.example.bykeria.ui.pages

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bykeria.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, context: Context) {
    var fadeOut by remember { mutableStateOf(false) }


    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.bykeria).apply {
            isLooping = false
            start()
        }
    }


    LaunchedEffect(Unit) {
        delay(2500)
        fadeOut = true
        delay(500)
        navController.navigate("home_screen") {
            popUpTo("splash") { inclusive = true }
        }
    }


    AnimatedVisibility(
        visible = !fadeOut,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F130)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logoo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "byKeria",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black
                    )
                )
            }
        }
    }


    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}