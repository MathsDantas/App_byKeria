package com.example.bykeria.ui.pages

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _isDarkTheme = MutableStateFlow(false) // Iniciado com o tema claro
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}
