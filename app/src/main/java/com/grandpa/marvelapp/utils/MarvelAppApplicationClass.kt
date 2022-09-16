package com.grandpa.marvelapp.utils

import android.app.Application

class MarvelAppApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        // initialize room db...
    }
}