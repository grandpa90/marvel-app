package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.StoriesEntity
import io.reactivex.Flowable

@Dao
interface StoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(storiesEntity: StoriesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(storiesEntity: StoriesEntity)

    @Query("SELECT * FROM STORIES_ENTITY")
    fun getAllStories(): Flowable<List<StoriesEntity>>

    @Query("SELECT * FROM STORIES_ENTITY where :_id")
    fun getStories(_id: Long): Flowable<StoriesEntity>

    @Query("DELETE FROM STORIES_ENTITY")
    fun deleteAllStories()

    @Query("DELETE FROM STORIES_ENTITY WHERE :_id")
    fun deleteStories(_id: Long)


}