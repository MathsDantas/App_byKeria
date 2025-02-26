package com.example.bykeria.ui.components

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.bykeria.notifications.NotificationHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Criando DataStore no contexto
private val Context.dataStore by preferencesDataStore(name = "settings_preferences")

class SettingsDataStore(private val context: Context) {
    // 🔹 Chaves de armazenamento
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    private val AUTO_THEME_KEY = booleanPreferencesKey("auto_theme") // Nova chave para o modo automático
    private val FAVORITE_POSTOS_KEY = stringPreferencesKey("favorite_postos")
    private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")

    // 🔹 Fluxo para o modo automático
    val isAutoTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[AUTO_THEME_KEY] ?: false }

    // 🔹 Salvar o modo automático
    suspend fun setAutoTheme(isAutoTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AUTO_THEME_KEY] = isAutoTheme
        }
    }


    // 🔹 Salvar o token JWT no DataStore
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[JWT_TOKEN_KEY] = token
        }
    }

    // 🔹 Recuperar o token JWT
    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[JWT_TOKEN_KEY]
    }

    // 🔹 Remover o token JWT (Logout)
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(JWT_TOKEN_KEY)
        }
    }

    // Fluxo para tema escuro
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[DARK_THEME_KEY] ?: false }

    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDarkTheme
        }
    }

    // Fluxo para postos favoritos
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
                currentFavorites.remove(postoId) // Remove se já for favorito
            } else {
                currentFavorites.add(postoId) // Adiciona se não for favorito

                // Notificação ao favoritar posto
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
