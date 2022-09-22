package com.grandpa.marvelapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.grandpa.marvelapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeNewSplash()
        setContentView(R.layout.activity_main)


    }

    private fun initializeNewSplash() {
        val splash = installSplashScreen()

    }

}