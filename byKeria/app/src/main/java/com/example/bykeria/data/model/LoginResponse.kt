package com.example.bykeria.data.model


data class LoginResponse(
    val username: String,
    val email: String,
    val admin: String,
    val id: Int,
    val jwt: String
)
