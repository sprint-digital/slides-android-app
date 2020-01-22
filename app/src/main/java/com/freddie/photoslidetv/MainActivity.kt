package com.freddie.photoslidetv

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private val photos = intArrayOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5
    )


    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        actionBar?.hide()
        /*fixedRateTimer(period = 1000){
            runOnUiThread {
                image.animate()
                image.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, photos[index]))
                if (index == photos.size - 1) index = 0 else index++
            }

        }*/


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var direction: Int = 1

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val nextPosition = position + direction
                Timer().schedule(2000) {
                    runOnUiThread {
                        viewPager.setCurrentItem(nextPosition, true)
                    }
                }
                if (nextPosition == photos.size - 1 || nextPosition == 0) {
                    direction *= -1
                }

            }
        })
        viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        viewPager.setCurrentItem(1, true)
    }


    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount(): Int = photos.size
        override fun getItem(position: Int): Fragment =
            ScreenSlidePageFragment.newInstance(photos[position])
    }
}
