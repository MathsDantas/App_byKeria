package com.example.bykeria.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bykeria.ui.components.SettingsDataStore
import kotlinx.coroutines.flow.first

class PostoWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val settingsDataStore = SettingsDataStore(applicationContext)
        val favoritos = settingsDataStore.favoritePostos.first()

        if (favoritos.isNotEmpty()) {
            val isOpening = inputData.getBoolean("isOpening", false)
            val message = if (isOpening) {
                "Seu posto favorito está abrindo!"
            } else {
                "Seu posto favorito está fechando em breve!"
            }

            NotificationHelper.showNotification(applicationContext, "Alerta de Posto", message)
        }

        return Result.success()
    }
}
