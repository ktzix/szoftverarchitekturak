package com.example.fastfashion.notification

import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // Your code to execute when Boot Completd

        val i = Intent(context, NewClothesService::class.java)
        context.startService(i)

        Toast.makeText(context, "Booting Completed", Toast.LENGTH_LONG).show()

    }

}