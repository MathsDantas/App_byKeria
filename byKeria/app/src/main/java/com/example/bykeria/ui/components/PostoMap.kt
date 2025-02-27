package com.example.bykeria.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun PostoMap(latitude: Double, longitude: Double) {

    val postoLocation = LatLng(latitude, longitude)


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(postoLocation, 15f)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp)
            .padding(vertical = 8.dp)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            Marker(
                state = MarkerState(position = postoLocation),
                title = "Local do Posto",
                snippet = "Clique para mais detalhes"
            )
        }
    }
}

