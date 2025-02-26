package com.example.bykeria.ui.components

import android.content.Context
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsDataStore: SettingsDataStore) : ViewModel() {
    // 🔹 Estado do tema escuro
    val isDarkTheme: Flow<Boolean> = settingsDataStore.isDarkTheme

    // 🔹 Estado do modo automático
    val isAutoTheme: Flow<Boolean> = settingsDataStore.isAutoTheme

    init {
        // Verifica o tema ao iniciar o ViewModel
        viewModelScope.launch {
            settingsDataStore.isAutoTheme.collect { isAutoTheme ->
                if (isAutoTheme) {
                    // Se o modo automático estiver ativado, define o tema com base no horário
                    val isNight = isNightTime()
                    settingsDataStore.setDarkTheme(isNight)
                }
            }
        }
    }

    // 🔹 Alternar o tema escuro
    fun setDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            settingsDataStore.setDarkTheme(isDarkTheme)
        }
    }

    // 🔹 Alternar o modo automático
    fun setAutoTheme(isAutoTheme: Boolean) {
        viewModelScope.launch {
            settingsDataStore.setAutoTheme(isAutoTheme)
            if (isAutoTheme) {
                // Se o modo automático for ativado, define o tema com base no horário
                val isNight = isNightTime()
                settingsDataStore.setDarkTheme(isNight)
            }
        }
    }

    // 🔹 Verificar o horário do sistema para definir o tema
    fun isNightTime(): Boolean {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour < 6 || hour >= 18 // Das 18:00 às 5:59 = modo escuro
    }
}