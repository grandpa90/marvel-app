package com.grandpa.marvelapp.managers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.activities.MainActivity
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context

object NotificationsManager {
    private val TAG = "NotificationsManager"
    private var didRegisterOnce: Boolean = false


    init {
        FirebaseApp.initializeApp(context)
    }

    fun getInstance(): NotificationsManager {
        return this
    }

    private fun shouldRegister(): Boolean {
        if (didRegisterOnce) {
            return false
        }
        didRegisterOnce = true
        return true
    }

    fun registerNow() {
        didRegisterOnce = false
        registerIfNeeded()
    }


    fun registerIfNeeded() {
        Log.wtf(TAG, "registering if needed for notification...")
        if (!shouldRegister()) {
            return
        }
        registerWithFCMAndGetToken()
    }


    private fun registerWithFCMAndGetToken() {
        Log.wtf(TAG, "registering in FCM and getting a token...")

        // keep a ref to self
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.wtf(TAG, "error registering FCM", task.exception)
                registerNow()
                return@OnCompleteListener
            }
            try {
                val token = task.result!!
                Log.wtf(TAG, "Got FCM Token=$token")


            } catch (ex: Exception) {
                Log.wtf(TAG, "Failed while registering with FCM$ex")
            }
        })
    }


    fun addNotification(
        context: Context,
        notId: Int,
        title: String,
        message: String
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = context.getString(R.string.default_notification_channel_id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                )
            notificationChannel.description = "OpenApp Notifications"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(500)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notificationBuilder
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setVibrate(longArrayOf(500))
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis()).priority = Notification.PRIORITY_MAX


        val resultIntent = Intent(context, MainActivity::class.java)
        val backIntent = Intent(context, MainActivity::class.java)
        backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val resultPendingIntent = PendingIntent.getActivities(
            context, notId, arrayOf(backIntent, resultIntent), PendingIntent.FLAG_ONE_SHOT
        )

        notificationBuilder.setContentIntent(resultPendingIntent)
        notificationManager.notify(notId, notificationBuilder.build())
    }

    fun dismissNotification(context: Context, notId: Int) {
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.cancel(notId)
    }
}