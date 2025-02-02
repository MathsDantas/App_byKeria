package com.example.bykeria.ui.components

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.bykeria.notifications.NotificationHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Criação do DataStore no contexto
private val Context.dataStore by preferencesDataStore(name = "settings_preferences")

class SettingsDataStore(private val context: Context) {

    // Chaves de armazenamento no DataStore
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    private val FAVORITE_POSTOS_KEY = stringPreferencesKey("favorite_postos")

    // Fluxo que observa as mudanças no tema
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    // Função para salvar o estado do tema
    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDarkTheme
        }
    }

    // Fluxo que observa os postos favoritos
    val favoritePostos: Flow<Set<Int>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITE_POSTOS_KEY]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet() ?: emptySet()
        }

    // Função para favoritar/desfavoritar um posto
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

                // Exibe notificação ao favoritar um posto
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
