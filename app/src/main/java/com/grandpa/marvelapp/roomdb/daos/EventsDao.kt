package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.EventsEntity
import io.reactivex.Flowable

/*
* dao for insert update delete and select
* for room db
* */
@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventsEntity: EventsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(eventsEntity: EventsEntity)

    @Query("SELECT * FROM events_entity")
    fun getAllEvents(): Flowable<List<EventsEntity>>

    @Query("SELECT * FROM events_entity where :_id")
    fun getEvent(_id: Long): Flowable<EventsEntity>

    @Query("DELETE FROM EVENTS_ENTITY")
    fun deleteAllEvenet()

    @Query("DELETE FROM EVENTS_ENTITY where :_id")
    fun deleteEvenet(_id: Long)


}