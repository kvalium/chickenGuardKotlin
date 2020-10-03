package com.example.chickenguard.helpers

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class App : Application() {
    private val channelId = "Progress Notification"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    //Check if the Android version is greater than 8. (Android Oreo)
    private fun createNotificationChannels() {
        val channel = NotificationChannel(
            channelId,
            "Progress Notification",
            //IMPORTANCE_HIGH = shows a notification as peek notification.
            //IMPORTANCE_LOW = shows the notification in the status bar.
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Progress Notification Channel"
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(channel)
    }
}