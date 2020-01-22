package com.freddie.photoslidetv

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*
import kotlin.concurrent.schedule

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == Intent.ACTION_BOOT_COMPLETED) {
            Timer().schedule(60000){
                val i = Intent(context, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context?.startActivity(i)
            }
        }
    }
}