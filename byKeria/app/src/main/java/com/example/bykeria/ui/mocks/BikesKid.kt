package com.example.bykeria.ui.mocks

import com.example.bykeria.R

data class BikesKid(
    val id: Int,
    val modelo: String,
    val tipo: String,
    val descricao: String,
    val imageRes: Int

)

val bikesKidList = listOf(
    BikesKid(
        id = 1,
        modelo = "Caloi Esportiva",
        tipo = "Esportiva Infantil",
        descricao = "Uma bicicleta feita para sua criança praticar o ciclismo junto com você!",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 2,
        modelo = "Monark Passeio",
        tipo = "Passeio Infantil",
        descricao = "Ideal para passeios tranquilos no final de semana.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 3,
        modelo = "Giro Urbano",
        tipo = "Urbana Infantil",
        descricao = "Perfeita para o dia a dia da cidade, leve e prática.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 4,
        modelo = "Caloi Kids",
        tipo = "Passeio Infantil",
        descricao = "A bike ideal para passeios longos, com conforto e estilo.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 5,
        modelo = "Oggi Speed",
        tipo = "Esportiva Infantil",
        descricao = "Desenvolvida para crianças que já amam pedalar com desempenho.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 6,
        modelo = "Sense Urban",
        tipo = "Urbana Infantil",
        descricao = "Modelo compacta e resistente, perfeita para as ruas da cidade.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 7,
        modelo = "GTS Passeio",
        tipo = "Passeio Infantil",
        descricao = "Conforto e estilo em uma bicicleta ideal para o lazer.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 8,
        modelo = "Caloi Speed",
        tipo = "Esportiva Infantil",
        descricao = "Uma bicicleta para os pequenos ciclistas que querem velocidade.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 9,
        modelo = "Daimler Kids",
        tipo = "Urbana Infantil",
        descricao = "Feita para garantir agilidade e conforto no trânsito urbano.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesKid(
        id = 10,
        modelo = "Oggi Passeio",
        tipo = "Passeio Infantil",
        descricao = "Uma bicicleta confortável para crianças aproveitarem o ar livre.",
        imageRes = R.drawable.ic_launcher_foreground
    )
)