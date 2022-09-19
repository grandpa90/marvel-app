package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.SeriesEntity
import io.reactivex.Flowable


@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(seriesEntity: SeriesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(seriesEntity: SeriesEntity)

    @Query("SELECT * FROM SERIES_ENTITY")
    fun getAllSeries(): Flowable<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_ENTITY WHERE :_id")
    fun getSeries(_id: Long): Flowable<SeriesEntity>

    @Query("DELETE FROM SERIES_ENTITY")
    fun deleteAllSeries()

    @Query("DELETE FROM SERIES_ENTITY WHERE:_id")
    fun deleteSeries(_id: Long)


}