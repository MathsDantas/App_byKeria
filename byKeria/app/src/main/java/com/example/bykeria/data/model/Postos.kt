package com.example.bykeria.data.model


data class PostosResponse(
    val data: List<Postos>
)

data class Postos(
    val id: Int,
    val nameUnidade: String,
    val endereco: String
)

