package com.example.bykeria.data.model

data class UserRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: String = "admin"
)
