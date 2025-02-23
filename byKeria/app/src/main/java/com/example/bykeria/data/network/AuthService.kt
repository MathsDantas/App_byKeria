package com.example.bykeria.data.network

import com.example.bykeria.data.model.LoginRequest
import com.example.bykeria.data.model.LoginResponse
import com.example.bykeria.data.model.PostoDetalhesResponse
import com.example.bykeria.data.model.Postos
import com.example.bykeria.data.model.PostosResponse
import com.example.bykeria.data.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("users")
    fun createUser(@Body user: UserRequest): Call<Void>

    @GET("postos") // Rota para obter todos os postos
    suspend fun getPostos(@Header("Authorization") authHeader: String): PostosResponse

    @GET("postos/{id}") // Rota para obter os detalhes de um posto específico
    suspend fun getPostoById(
        @Path("id") id: Int,
        @Header("Authorization") authHeader: String
    ): Response<PostoDetalhesResponse> // Adicione uma classe de resposta para os detalhes do posto
}
