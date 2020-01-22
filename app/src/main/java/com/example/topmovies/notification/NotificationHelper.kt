package com.example.topmovies.notification

import android.app.*
import android.content.Context
import android.os.Build
import com.example.topmovies.R
import android.app.NotificationManager

import android.app.NotificationChannel
import android.annotation.TargetApi
import android.content.ContextWrapper
import android.support.v4.app.NotificationCompat


class NotificationHelper(base: Context) : ContextWrapper(base) {

    private var mManager: NotificationManager? = null
    var channelID = "channelID_"
    val manager: NotificationManager?
        get() {
            if (mManager == null) mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            return mManager
        }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel()
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun createChannel() {
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = R.color.colorPrimary
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        manager!!.createNotificationChannel(channel)

    }

    fun getChannelNotification(title: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("You've scheduled to watch $title")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
    }

    companion object {
        val channelID = "channelID_"
        val channelName = "channel"
    }
}




