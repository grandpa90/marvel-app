package com.grandpa.marvelapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grandpa.marvelapp.roomdb.daos.*
import com.grandpa.marvelapp.roomdb.entities.*
import com.grandpa.marvelapp.utils.Constants

@Database(
    entities = [CharacterEntity::class,
        ComicsEntity::class, EventsEntity::class,
        SeriesEntity::class, StoriesEntity::class],
    version = 1, exportSchema = false
)

abstract class MarvelRoomDB : RoomDatabase() {
    abstract fun CharacterDao(): CharacterDao
    abstract fun ComicsDao(): ComicsDao
    abstract fun EventsDao(): EventsDao
    abstract fun SeriesDao(): SeriesDao
    abstract fun StoriesDao(): StoriesDao

    companion object {
        @Volatile
        private var INSTANCE: MarvelRoomDB? = null
        fun getDatabase(context: Context): MarvelRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelRoomDB::class.java,
                    Constants.ROOM_DB.ROOM_DB_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }


}