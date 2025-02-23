package com.example.bykeria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bykeria.data.model.LoginRequest
import com.example.bykeria.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.login(LoginRequest(username, password))
                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()!!.jwt
                    // TODO: Salvar o token em SharedPreferences ou DataStore
                    onResult(true) // Login bem-sucedido
                } else {
                    onResult(false) // Login falhou
                }
            } catch (e: Exception) {
                onResult(false) // Erro na requisição
            }
        }
    }
}
