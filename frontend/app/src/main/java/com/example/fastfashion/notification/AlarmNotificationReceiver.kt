package com.example.fastfashion.notification

import android.content.BroadcastReceiver
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.app.Notification
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.PendingIntent
import android.content.Context
import com.example.fastfashion.MainActivity
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.app.NotificationChannel
import android.net.Uri
import android.system.Os.link






class AlarmNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val builder = NotificationCompat.Builder(context)

        val myIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            myIntent,
            FLAG_ONE_SHOT
        )
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel("YOUR_CHANNEL_ID",
                "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"
            notificationManager.createNotificationChannel(channel)
        }
        val noti= builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentTitle("Elavult ruhadarabok")
           // .setContentIntent(pendingIntent)
            .setChannelId("YOUR_CHANNEL_ID")
            .setSmallIcon(com.example.fastfashion.R.drawable.ic_launcher_foreground)
            .setContentText("Ideje lenne új ruhadarabokat beszerezni! Nézd meg a legújabb ajánlatokat!")
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
            .setContentInfo("Info")

        val notificationIntent = Intent(Intent.ACTION_VIEW)
        notificationIntent.data = Uri.parse("https://www.vangraaf.com/hu/")

        val pending = PendingIntent.getActivity(context, 0, notificationIntent, FLAG_ONE_SHOT)
        noti.setContentIntent(pending)


        Log.i("notifi", "noti")
        notificationManager.notify(1, builder.build())
    }
}