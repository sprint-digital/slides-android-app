package com.freddie.photoslidetv

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    private val photos = intArrayOf(R.drawable.photo_1,R.drawable.photo_3,R.drawable.photo_3,R.drawable.photo_4, R.drawable.photo_5)
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT < 16)
        {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        else
        {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        actionBar?.hide()
        fixedRateTimer(period = 1000){
            runOnUiThread {
                image.animate()
                image.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, photos[index]))
                if (index == photos.size - 1) index = 0 else index++
            }

        }
    }
}
