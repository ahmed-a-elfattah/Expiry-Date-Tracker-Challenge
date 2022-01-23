package com.aelfattah.ahmed.expirydatetrackerchallenge

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.aelfattah.ahmed.expirydatetrackerchallenge.di.allModules
import com.aelfattah.ahmed.expirydatetrackerchallenge.utils.PeriodicBackgroundNotification.Companion.CHANNEL_ID_PERIOD_WORK
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        createNotificationChannel()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(allModules)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelPeriodic =
                NotificationChannel(CHANNEL_ID_PERIOD_WORK, "Period Work Request", importance)
            channelPeriodic.description = "Periodic Work"
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = applicationContext.getSystemService(
                NotificationManager::class.java
            )
            notificationManager!!.createNotificationChannel(channelPeriodic)
        }
    }

}