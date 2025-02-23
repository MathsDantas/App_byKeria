package com.example.bykeria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bykeria.data.model.Postos
import com.example.bykeria.data.network.AuthService
import com.example.bykeria.ui.components.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostosViewModel(private val dataStore: SettingsDataStore) : ViewModel() {

    private val _postos = MutableStateFlow<List<Postos>>(emptyList())
    val postos: StateFlow<List<Postos>> = _postos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://backend-bykeria.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postoService = retrofit.create(AuthService::class.java)

    fun fetchPostos() {
        viewModelScope.launch {
            _isLoading.value = true

            dataStore.tokenFlow.collect { token ->
                if (!token.isNullOrEmpty()) {
                    try {
                        // Fazendo a requisição e passando o token para o header
                        val response = postoService.getPostos("Bearer $token")

                        // Verifica se a resposta não é nula
                        if (response != null) {
                            // Acessando corretamente o campo "data" da resposta
                            _postos.value = response.data // response.data é a lista de Postos
                        } else {
                            _postos.value = emptyList() // Se não tiver resposta, usa lista vazia
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        _isLoading.value = false
                    }
                } else {
                    _isLoading.value = false
                }
            }
        }
    }

}

class PostosViewModelFactory(
    private val settingsDataStore: SettingsDataStore // Corrigido para SettingsDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostosViewModel(settingsDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
