package com.example.bykeria.ui.mocks

import com.example.bykeria.R

data class BikesAdu(
    val id: Int,
    val modelo: String,
    val tipo: String,
    val descricao: String,
    val imageRes: Int
)

val bikesAduList = listOf(
    BikesAdu(
        id = 1,
        modelo = "Caloi Esportiva Pro",
        tipo = "Esportiva",
        descricao = "A bicicleta ideal para ciclistas de desempenho que buscam velocidade e eficiência nas trilhas.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 2,
        modelo = "Monark Passeio Urbano",
        tipo = "Passeio",
        descricao = "Conforto e praticidade para quem busca um transporte ágil na cidade.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 3,
        modelo = "Giro Urban Pro",
        tipo = "Urbana",
        descricao = "Feita para o cotidiano na cidade, com design arrojado e resistência.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 4,
        modelo = "Caloi Turismo",
        tipo = "Passeio",
        descricao = "Perfeita para quem adora passeios longos e confortáveis em estradas e trilhas leves.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 5,
        modelo = "Oggi Racing",
        tipo = "Esportiva",
        descricao = "Com design aerodinâmico e desempenho superior, essa bike é feita para as competições.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 6,
        modelo = "Sense Urban Plus",
        tipo = "Urbana",
        descricao = "Bike urbana com rodas largas e confortável, ideal para o dia a dia na cidade.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 7,
        modelo = "GTS Conforto",
        tipo = "Passeio",
        descricao = "Com o máximo de conforto para passeios tranquilos, essa bike é perfeita para relaxar no fim de semana.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 8,
        modelo = "Caloi Speed Pro",
        tipo = "Esportiva",
        descricao = "Alta performance para ciclistas experientes, projetada para alcançar altas velocidades.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 9,
        modelo = "Daimler Urbano",
        tipo = "Urbana",
        descricao = "Ideal para quem precisa de uma bike prática para o transporte diário pela cidade.",
        imageRes = R.drawable.ic_launcher_foreground
    ),
    BikesAdu(
        id = 10,
        modelo = "Oggi City",
        tipo = "Passeio",
        descricao = "A bicicleta de passeio mais confortável para explorar a cidade com estilo e praticidade.",
        imageRes = R.drawable.ic_launcher_foreground
    )
)
