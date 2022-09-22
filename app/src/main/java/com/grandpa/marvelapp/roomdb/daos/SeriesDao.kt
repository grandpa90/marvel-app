package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.SeriesEntity
import io.reactivex.Flowable

/*
* dao for insert update delete and select
* for room db
* */
@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(seriesEntity: SeriesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(seriesEntity: SeriesEntity)

    @Query("SELECT * FROM SERIES_ENTITY")
    fun getAllSeries(): Flowable<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_ENTITY WHERE  series_id = :id")
    fun getSeries(id: Long): Flowable<SeriesEntity>

    @Query("DELETE FROM SERIES_ENTITY")
    fun deleteAllSeries()

    @Query("DELETE FROM SERIES_ENTITY WHERE series_id = :id")
    fun deleteSeries(id: Long)


}