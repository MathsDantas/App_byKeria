package com.example.bykeria.ui.components

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context) : ViewModel() {

    private val dataStore = SettingsDataStore(context)

    // Fluxo que representa o estado do tema (escuro ou claro)
    val isDarkTheme: StateFlow<Boolean> = dataStore.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Método para alterar o estado do tema
    fun setDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            dataStore.setDarkTheme(isDarkTheme)
        }
    }
}
