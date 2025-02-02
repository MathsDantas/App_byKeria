package com.example.bykeria.notifications

import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

object NotificationScheduler {
    fun schedulePostoNotifications(context: Context) {
        scheduleNotification(context, 7, 0, true)  // Notificação de abertura (07:00)
        scheduleNotification(context, 18, 0, false) // Notificação de fechamento (18:00)
    }

    private fun scheduleNotification(context: Context, hour: Int, minute: Int, isOpening: Boolean) {
        val now = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(now)) { // Se o horário já passou hoje, agendar para amanhã
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val delay = targetTime.timeInMillis - now.timeInMillis

        val workRequest = OneTimeWorkRequestBuilder<PostoWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("isOpening" to isOpening))
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
