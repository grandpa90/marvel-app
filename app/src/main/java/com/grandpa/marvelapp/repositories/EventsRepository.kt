package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.EventsDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.EventsDao
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EventsRepository(application: Application) {

    var eventsDao: EventsDao
    var db: MarvelRoomDB

    init {
        db = MarvelRoomDB.getDatabase(application)
        eventsDao = db.EventsDao()
    }


    fun insertEvents(eventsDto: EventsDto) {
        Completable.create {
            eventsDao.insert(eventsDto.toEventsEntity())
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

    fun updateEvents(eventsDto: EventsDto) {
        Completable.create {
            eventsDao.update(eventsDto.toEventsEntity())
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

    fun deleteEvents() {
        Completable.create {
            eventsDao.deleteAllEvenet()
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

    fun deleteEvent(_id: Long) {
        Completable.create {
            eventsDao.deleteEvenet(_id)
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

    fun getAllEvents(): Flowable<List<EventsDto>> {
        lateinit var eventsDto: EventsDto
        val flowableList = eventsDao.getAllEvents()
        return flowableList.map {
            it.map { events ->
                eventsDto = events.toEventsDto()
                eventsDto
            }
        }
    }

    fun getEvent(_id: Long): Flowable<EventsDto> {
        lateinit var eventsDto: EventsDto
        val flowable = eventsDao.getEvent(_id)
        return flowable.map {
            eventsDto = it.toEventsDto()
            eventsDto
        }
    }

    fun getEventsRemote(characterId: Long): Flowable<List<EventsDto>> {
        lateinit var eventsDto: EventsDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getEvents(characterId = characterId)
        return flowableList.map {
            it.data.result.map { event ->
                eventsDto = event.eventsToDto()
                eventsDao.insert(eventsDto.toEventsEntity())
                eventsDto
            }
        }
    }


}