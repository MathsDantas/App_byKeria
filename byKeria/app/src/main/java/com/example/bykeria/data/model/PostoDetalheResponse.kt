package com.example.bykeria.data.model

data class PostoDetalhesResponse(
    val data: PostoDetalhes
)

data class PostoDetalhes(
    val id: Int,
    val nameUnidade: String,
    val endereco: String,
    val bikes: List<Bike>
)

data class Bike(
    val id: Int,
    val type: String,
    val status: String
)