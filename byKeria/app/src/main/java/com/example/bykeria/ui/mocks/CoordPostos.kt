package com.example.bykeria.ui.mocks

import com.google.android.gms.maps.model.LatLng


val mockCoordinates = mapOf(
    1 to LatLng(-5.19764090197821, -39.2951502234569),
    2 to LatLng(-4.541179575483994, -40.71721419121439),
    3 to LatLng(33.977739832481504, -118.46412484578205),
    4 to LatLng(-22.98423884464502, -43.22343062039156)
)


fun getMockCoordinates(postoId: Int): LatLng {
    return mockCoordinates[postoId] ?: LatLng(0.0, 0.0)
}