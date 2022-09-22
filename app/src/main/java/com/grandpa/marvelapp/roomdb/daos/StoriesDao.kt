package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.StoriesEntity
import io.reactivex.Flowable
/*
* dao for insert update delete and select
* for room db
* */
@Dao
interface StoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(storiesEntity: StoriesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(storiesEntity: StoriesEntity)

    @Query("SELECT * FROM STORIES_ENTITY")
    fun getAllStories(): Flowable<List<StoriesEntity>>

    @Query("SELECT * FROM STORIES_ENTITY where stories_id = :id")
    fun getStories(id: Long): Flowable<StoriesEntity>

    @Query("DELETE FROM STORIES_ENTITY")
    fun deleteAllStories()

    @Query("DELETE FROM STORIES_ENTITY WHERE stories_id = :id")
    fun deleteStories(id: Long)


}