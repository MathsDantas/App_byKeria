package com.example.bykeria.data.network

import com.example.bykeria.data.model.LoginRequest
import com.example.bykeria.data.model.LoginResponse
import com.example.bykeria.data.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("users")
    fun createUser(@Body user: UserRequest): Call<Void>
}
