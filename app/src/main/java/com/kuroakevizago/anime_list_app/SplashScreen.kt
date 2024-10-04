package com.kuroakevizago.anime_list_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // Use a handler to delay the launch of the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the main activity after the splash screen
            startActivity(Intent(this, AnimeList::class.java))
            finish()
        }, 2000) // Delay in milliseconds (2 seconds)
    }
}