package com.grandpa.marvelapp.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.managers.NotificationsManager
import kotlin.random.Random

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        sendToServer()
    }

    private fun sendToServer() {
        NotificationsManager.registerNow()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // messages, here is where that should be initiated.

        try {
            // fastLog(TAG + " From: " + remoteMessage!!.from)
            val title = remoteMessage.data["title"] ?: getString(R.string.app_name)
            val message = remoteMessage.data["message"] ?: ""


            NotificationsManager.addNotification(
                context = this,
                notId = Random.nextInt(1000),
                title = title,
                message = message
            )


        } catch (e: Exception) {
            //  crashLog("Notification crash ", e)
        }
    }
}