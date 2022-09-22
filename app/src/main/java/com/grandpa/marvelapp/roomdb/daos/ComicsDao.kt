package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.ComicsEntity
import io.reactivex.Flowable
/*
* dao for insert update delete and select
* for room db
* */
@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comicsEntity: ComicsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(comicsEntity: ComicsEntity)

    @Query("SELECT * FROM COMICSENTITY")
    fun getAllComics(): Flowable<List<ComicsEntity>>

    @Query("SELECT * FROM COMICSENTITY where comics_id =:id")
    fun getComics(id: Long): Flowable<ComicsEntity>

    @Query("DELETE FROM COMICSENTITY")
    fun deleteAllComics()

    @Query("DELETE FROM COMICSENTITY where comics_id = :id")
    fun deleteComics(id: Long)


}