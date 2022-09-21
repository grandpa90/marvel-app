package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.SeriesDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.SeriesDao
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SeriesRepository(application: Application) {

    var seriesDao: SeriesDao
    var db: MarvelRoomDB

    init {
        db = MarvelRoomDB.getDatabase(application)
        seriesDao = db.SeriesDao()
    }

    fun insertSeries(seriesDto: SeriesDto) {
        Completable.create {
            seriesDao.insert(seriesDto.toSeriesEntity())
            it.onComplete()
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

    fun updateSeries(seriesDto: SeriesDto) {
        Completable.create {
            seriesDao.insert(seriesDto.toSeriesEntity())
            it.onComplete()
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

    fun deleteAllSeries() {
        Completable.create {
            seriesDao.deleteAllSeries()
            it.onComplete()
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

    fun deleteSeries(_id: Long) {
        Completable.create {
            seriesDao.deleteSeries(_id)
            it.onComplete()
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

    fun getSeries(_id: Long): Flowable<SeriesDto> {
        lateinit var seriesDto: SeriesDto
        val flowable = seriesDao.getSeries(_id)
        return flowable.map {
            seriesDto = it.toSeriesDto()
            seriesDto
        }
    }

    fun getSeriesRemote(characterId: Long): Flowable<List<SeriesDto>> {
        lateinit var seriesDto: SeriesDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getSeries(characterId = characterId)
        return flowableList.map {
            it.data.result.map { series ->
                seriesDto = series.toSeriesDto()
                seriesDao.insert(seriesDto.toSeriesEntity())
                seriesDto
            }
        }
    }

}