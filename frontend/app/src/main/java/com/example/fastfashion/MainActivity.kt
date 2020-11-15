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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNewItem.setOnClickListener {

            val intent = Intent(applicationContext, UploadActivity::class.java)
            startActivity(intent)
        }
        btnList.setOnClickListener {
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
        startAlarm(true, true)

    }

    private fun startAlarm(isNotification: Boolean, isRepeat: Boolean) {
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val myIntent: Intent
        val pendingIntent: PendingIntent

        // SET TIME HERE
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 17)
        calendar.set(Calendar.MINUTE, 44)


        myIntent = Intent(this@MainActivity, AlarmNotificationReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0)

Log.i("1", "1")
        if (!isRepeat) {
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent)

            Log.i("2", "2")
        }else{
        Log.i("3", "3")
            manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )}
    }
}
