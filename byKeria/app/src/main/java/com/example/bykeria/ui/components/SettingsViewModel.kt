package com.example.bykeria.ui.components


import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsDataStore: SettingsDataStore) : ViewModel() {

    val isDarkTheme: Flow<Boolean> = settingsDataStore.isDarkTheme


    val isAutoTheme: Flow<Boolean> = settingsDataStore.isAutoTheme

    init {

        viewModelScope.launch {
            settingsDataStore.isAutoTheme.collect { isAutoTheme ->
                if (isAutoTheme) {

                    val isNight = isNightTime()
                    settingsDataStore.setDarkTheme(isNight)
                }
            }
        }
    }


    fun setDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            settingsDataStore.setDarkTheme(isDarkTheme)
        }
    }


    fun setAutoTheme(isAutoTheme: Boolean) {
        viewModelScope.launch {
            settingsDataStore.setAutoTheme(isAutoTheme)
            if (isAutoTheme) {

                val isNight = isNightTime()
                settingsDataStore.setDarkTheme(isNight)
            }
        }
    }


    fun isNightTime(): Boolean {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour < 6 || hour >= 18
    }
}