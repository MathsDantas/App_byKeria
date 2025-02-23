package com.example.bykeria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bykeria.data.model.PostoDetalhes
import com.example.bykeria.data.network.AuthService
import com.example.bykeria.ui.components.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetalhesPostoViewModel(
    private val authService: AuthService,
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _posto = MutableStateFlow<PostoDetalhes?>(null)
    val posto: StateFlow<PostoDetalhes?> = _posto

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPostoDetalhes(postoId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val token = settingsDataStore.tokenFlow.first()
                if (!token.isNullOrEmpty()) {
                    val response = authService.getPostoById(postoId, "Bearer $token")
                    if (response.isSuccessful && response.body() != null) {
                        _posto.value = response.body()!!.data
                    } else {
                        _error.value = "Erro ao buscar detalhes do posto"
                    }
                } else {
                    _error.value = "Token de autenticação não encontrado"
                }
            } catch (e: Exception) {
                _error.value = "Erro na requisição: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

class DetalhesPostoViewModelFactory(
    private val authService: AuthService,
    private val settingsDataStore: SettingsDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalhesPostoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalhesPostoViewModel(authService, settingsDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}