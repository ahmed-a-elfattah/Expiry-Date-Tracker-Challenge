package com.aelfattah.ahmed.expirydatetrackerchallenge.utils

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aelfattah.ahmed.expirydatetrackerchallenge.R
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.MainActivity

class PeriodicBackgroundNotification(
    private val context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    companion object {
        const val CHANNEL_ID_PERIOD_WORK = "periodic"
    }

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(intent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_PERIOD_WORK).apply {
            setContentIntent(pendingIntent)
        }
        notification.setContentTitle("PERIOD WORK")
        notification.priority = NotificationCompat.PRIORITY_HIGH
        notification.setCategory(NotificationCompat.CATEGORY_ALARM)
        notification.setSmallIcon(R.drawable.ic_launcher_foreground)
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        notification.setSound(sound)
        val vibrate = longArrayOf(0, 100, 200, 300)
        notification.setVibrate(vibrate)

        with(NotificationManagerCompat.from(context)) {
            notify(1, notification.build())
        }
    }

}