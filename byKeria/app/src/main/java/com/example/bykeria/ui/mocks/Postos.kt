package com.example.bykeria.ui.mocks

import com.example.bykeria.R

data class Postos(
    val id: Int,
    val unidade: String,
    val endereco: String,
    val imageRes: Int,
    val quantidadeBikes: Int
)

val postosList = listOf(
    Postos(
        id = 1,
        unidade = "Boa Vista",
        endereco = "Rua Santa Thais Lara, 777",
        imageRes = R.drawable.posto1,
        quantidadeBikes = 8
    ),
    Postos(
        id = 2,
        unidade = "Universitário",
        endereco = "Rua São João Victor, 24",
        imageRes = R.drawable.posto2,
        quantidadeBikes = 10
    ),
    Postos(
        id = 3,
        unidade = "Boa Viagem",
        endereco = "Rua São Matheus Dantas, 1997",
        imageRes = R.drawable.posto3,
        quantidadeBikes = 13
    ),
    Postos(
        id = 4,
        unidade = "Cemitério",
        endereco = "Rua Santa Tânia, 1700",
        imageRes = R.drawable.posto1,
        quantidadeBikes = 5
    )
)