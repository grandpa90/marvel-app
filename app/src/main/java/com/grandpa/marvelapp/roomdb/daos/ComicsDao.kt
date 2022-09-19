package com.grandpa.marvelapp.roomdb.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grandpa.marvelapp.roomdb.entities.ComicsEntity
import io.reactivex.Flowable

interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comicsEntity: ComicsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(comicsEntity: ComicsEntity)

    @Query("SELECT * FROM COMICSENTITY")
    fun getAllComics(): Flowable<List<ComicsEntity>>

    @Query("SELECT * FROM COMICSENTITY where :_id")
    fun getComics(_id: Long): Flowable<ComicsEntity>

    @Query("DELETE FROM COMICSENTITY")
    fun deleteAllComics()

    @Query("DELETE FROM COMICSENTITY where:_id")
    fun deleteComics(_id: Long)


}