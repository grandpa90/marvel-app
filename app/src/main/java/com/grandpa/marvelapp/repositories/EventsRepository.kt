package com.grandpa.marvelapp.repositories

import android.util.Log
import com.grandpa.marvelapp.model.dto.EventsDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.EventsDao
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// repository for manipulating data
class EventsRepository() {
    // declaring interface and room db
    var eventsDao: EventsDao
    var db: MarvelRoomDB

    init {
        // initializing interface and room db
        db = MarvelRoomDB.getDatabase(context = context)
        eventsDao = db.EventsDao()
    }

    // insert into room db with completable observer
    fun insertEvents(eventsDto: EventsDto) {
        Completable.create {
            eventsDao.insert(eventsDto.toEventsEntity())
            it.onComplete() // signal streamer to be completed
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

    // update into room db with completable observer
    fun updateEvents(eventsDto: EventsDto) {
        Completable.create {
            eventsDao.update(eventsDto.toEventsEntity())
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
    fun deleteEvents() {
        Completable.create {
            eventsDao.deleteAllEvenet()
            it.onComplete() // signal the streamer
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

    // delete by id with completable observer
    fun deleteEvent(_id: Long) {
        Completable.create {
            eventsDao.deleteEvenet(_id)
            it.onComplete() // singal the observer
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

    // get all from room db with flowable observer
    // mapping entities into dto
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

    // get by id  from room db with flowable observer
    // mapping entities into dto
    fun getEvent(_id: Long): Flowable<EventsDto> {
        lateinit var eventsDto: EventsDto
        val flowable = eventsDao.getEvent(_id)
        return flowable.map {
            eventsDto = it.toEventsDto()
            eventsDto
        }
    }
    // get from remote db and insert into room db
    // mapping entities into dto

    fun getEventsRemote(characterId: Long): Flowable<List<EventsDto>> {
        lateinit var eventsDto: EventsDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getEvents(characterId = characterId)
        return flowableList.map {
            Log.wtf("E123", it.toString())

            it.data.results.map { event ->
                eventsDto = event.eventsToDto()
                eventsDao.insert(eventsDto.toEventsEntity())
                eventsDto
            }
        }
    }


}