package com.example.topmovies.notification

import android.app.Notification
import android.content.Context
import android.content.Intent

import android.content.BroadcastReceiver


class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra("title")
        val id = intent.getIntExtra("id", 1)
        val notificationHelper = NotificationHelper(context)
        val nb = notificationHelper.getChannelNotification(title)
        notificationHelper.manager!!.notify(id, nb.build())

    }
}


