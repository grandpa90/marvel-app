package com.grandpa.marvelapp.repositories

import com.grandpa.marvelapp.model.dto.SeriesDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.SeriesDao
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// repository for manipulating the data
class SeriesRepository() {
    // declaring interface and room db
    var seriesDao: SeriesDao
    var db: MarvelRoomDB

    init {
        // initializing room db and interface
        db = MarvelRoomDB.getDatabase(context)
        seriesDao = db.SeriesDao()
    }

    // insert into room db with completable observer
    fun insertSeries(seriesDto: SeriesDto) {
        Completable.create {
            seriesDao.insert(seriesDto.toSeriesEntity())
            it.onComplete() // signal the streamer to be completed
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                }

            })
    }

    // update room db with completable observer
    fun updateSeries(seriesDto: SeriesDto) {
        Completable.create {
            seriesDao.insert(seriesDto.toSeriesEntity())
            it.onComplete() // signal the streamer to be completed
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    // delete all from room db with completable observer
    fun deleteAllSeries() {
        Completable.create {
            seriesDao.deleteAllSeries()
            it.onComplete() // signal the streamer to be completed
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    // delete by id from room db with completable observer
    fun deleteSeries(_id: Long) {
        Completable.create {
            seriesDao.deleteSeries(_id)
            it.onComplete() // signal the streamer to be completed
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    // get all data from room db with Flowable observer
    // mapping entities into dto
    fun getAllSeries(): Flowable<List<SeriesDto>> {
        lateinit var seriesDto: SeriesDto
        val flowableList = seriesDao.getAllSeries()
        return flowableList.map {
            it.map { series ->

                series.toSeriesDto()
                seriesDto = series.toSeriesDto()
                seriesDto
            }
        }
    }

    // get by id  data from room db with Flowable observer
    // mapping entities into dto
    fun getSeries(_id: Long): Flowable<SeriesDto> {
        lateinit var seriesDto: SeriesDto
        val flowable = seriesDao.getSeries(_id)
        return flowable.map {
            seriesDto = it.toSeriesDto()
            seriesDto
        }
    }

    // get from remote db with flowable observer
    // mapping api model into dto
    fun getSeriesRemote(characterId: Long): Flowable<List<SeriesDto>> {
        lateinit var seriesDto: SeriesDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getSeries(characterId = characterId)
        return flowableList.map {
            it.data.results.map { series ->
                seriesDto = series.toSeriesDto()
                seriesDao.insert(seriesDto.toSeriesEntity())
                seriesDto
            }
        }
    }

}