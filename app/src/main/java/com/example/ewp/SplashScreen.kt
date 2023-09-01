package com.example.ewp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide



import java.util.Timer
import java.util.TimerTask


class SplashScreen : AppCompatActivity() {
    private var splashTimer: Timer? = null
    private val DELAY: Long = 1000
    private var scheduled = false
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val ll= findViewById<LottieAnimationView>(R.id.lottie_anim)
        ll.playAnimation()

//        val gifImageView = findViewById<ImageView>(R.id.gifImageView)
//        Glide.with(this)
//            .asGif()
//            .load(R.drawable.ewpsplash)
//            .fitCenter().into(gifImageView)
//        Log.d("android","splash screen")
//            .submit().get() as GifDrawable

//        val frameCount = gifDrawable.frameCount
//        val speedFactor = 0.5f // Adjust this value to change the speed (e.g., 2.0f for double speed)
//
//        for (i in 0 until frameCount) {
//            val frame = gifDrawable.getFrame(i)
//            val duration = frame.duration
//            frame.duration = (duration / speedFactor).toInt()
//        }



        context = this
        splashTimer = Timer()

        splashTimer!!.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
                finishAfterTransition()
            }
        }, DELAY)
        scheduled = true
    }
}