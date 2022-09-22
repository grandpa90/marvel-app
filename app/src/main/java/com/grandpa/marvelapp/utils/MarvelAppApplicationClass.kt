package com.grandpa.marvelapp.utils

import android.app.Application
import com.grandpa.marvelapp.roomdb.MarvelRoomDB

class MarvelAppApplicationClass: Application() {

    companion object {
        // declaring variable of room db
        lateinit var marvelRoomDB: MarvelRoomDB
        lateinit var context: MarvelAppApplicationClass

    }

    override fun onCreate() {
        super.onCreate()
        // initialize room db on application class

        context = this
        marvelRoomDB = MarvelRoomDB.getDatabase(this)
        val database by lazy { MarvelRoomDB.getDatabase(this) }


    }
}