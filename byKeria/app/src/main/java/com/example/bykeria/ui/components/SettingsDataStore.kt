package com.example.bykeria.ui.components

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.bykeria.notifications.NotificationHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "settings_preferences")

class SettingsDataStore(private val context: Context) {

    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    private val AUTO_THEME_KEY = booleanPreferencesKey("auto_theme") // Nova chave para o modo automático
    private val FAVORITE_POSTOS_KEY = stringPreferencesKey("favorite_postos")
    private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")


    val isAutoTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[AUTO_THEME_KEY] ?: false }


    suspend fun setAutoTheme(isAutoTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AUTO_THEME_KEY] = isAutoTheme
        }
    }



    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[JWT_TOKEN_KEY] = token
        }
    }


    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[JWT_TOKEN_KEY]
    }


    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(JWT_TOKEN_KEY)
        }
    }


    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[DARK_THEME_KEY] ?: false }

    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDarkTheme
        }
    }


    val favoritePostos: Flow<Set<Int>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITE_POSTOS_KEY]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet() ?: emptySet()
        }

    suspend fun toggleFavoritePosto(postoId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_POSTOS_KEY]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?.toMutableSet() ?: mutableSetOf()

            if (postoId in currentFavorites) {
                currentFavorites.remove(postoId)
            } else {
                currentFavorites.add(postoId)


                NotificationHelper.showNotification(
                    context,
                    "Posto Favoritado",
                    "Você adicionou um posto aos favoritos!"
                )
            }

            preferences[FAVORITE_POSTOS_KEY] = currentFavorites.joinToString(",")
        }
    }
}
