package com.example.bykeria.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun PostoMap(latitude: Double, longitude: Double) {
    // Coordenadas do posto
    val postoLocation = LatLng(latitude, longitude)

    // Estado da câmera (zoom e posição)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(postoLocation, 15f) // Zoom inicial
    }

    // Mapa do Google
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp) // Altura do mapa
            .padding(vertical = 8.dp)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Marcador no mapa
            Marker(
                state = MarkerState(position = postoLocation),
                title = "Local do Posto",
                snippet = "Clique para mais detalhes"
            )
        }
    }
}

