package com.example.bykeria.data.model


data class LoginResponse(
    val data: LoginData
)

data class LoginData(
    val username: String,
    val email: String,
    val role: String,
    val id: Int,
    val jwt: String
)

