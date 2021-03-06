package com.example.fastfashion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlarmManager
import android.os.SystemClock
import android.app.PendingIntent
import android.content.Context
import com.example.fastfashion.notification.AlarmNotificationReceiver
import android.content.Context.ALARM_SERVICE
import android.util.Log
import java.util.*


class MainActivity : AppCompatActivity() {
    private var userId =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title=getString(R.string.title_Main)
        setContentView(R.layout.activity_main)
        userId=intent.getIntExtra("id", 0)
        btnNewItem.setOnClickListener {

            val intent = Intent(applicationContext, UploadActivity::class.java)
            intent.putExtra("id", userId)
            startActivity(intent)
        }
        btnList.setOnClickListener {
            val intent = Intent(applicationContext, ListActivity::class.java)
            intent.putExtra("id", userId)
            startActivity(intent)
        }
        btnLogout.setOnClickListener {
            onBackPressed()
        }
        startAlarm(true, true)

    }

    private fun startAlarm(isNotification: Boolean, isRepeat: Boolean) {
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val myIntent: Intent
        val pendingIntent: PendingIntent

        // SET TIME HERE
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 41)


        myIntent = Intent(this@MainActivity, AlarmNotificationReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0)

        if (!isRepeat) {
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent)

        }else{
            manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )}
    }
}
