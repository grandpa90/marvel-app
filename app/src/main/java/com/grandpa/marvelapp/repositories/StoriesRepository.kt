package com.grandpa.marvelapp.repositories

import android.util.Log
import com.grandpa.marvelapp.model.dto.StoriesDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.StoriesDao
import com.grandpa.marvelapp.roomdb.entities.StoriesEntity
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// repository for manipulating the data

class StoriesRepository() {
    // decalring interface and room db
    private var storiesDao: StoriesDao
    private var db: MarvelRoomDB

    init {
        // initializing the room db and interface
        db = MarvelRoomDB.getDatabase(context = context)
        storiesDao = db.StoriesDao()
    }

    // insert into room db with completable observer
    // signal the streamer to be completed
    fun insertStory(storiesDto: StoriesDto) {
        Completable.create {
            storiesDao.insert(
                StoriesEntity(
                    storiesDto._id, storiesDto.description,
                    storiesDto.description, storiesDto.thumbnail
                )
            )
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

    // update  into room db with completable observer
    // signal the streamer to be completed
    fun updateStory(storiesDto: StoriesDto) {
        Completable.create {
            storiesDao.update(
                StoriesEntity(
                    storiesDto._id, storiesDto.description,
                    storiesDto.description, storiesDto.thumbnail
                )
            )
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

    // delete all from room db
    fun deleteAllStories() {
        Completable.create {
            storiesDao.deleteAllStories()
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

    // delete by id from room db
    fun deleteStories(_id: Long) {
        Completable.create {
            storiesDao.deleteStories(_id)
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

    // get all from  room db with completable observer
    fun getStories(): Flowable<List<StoriesDto>> {
        lateinit var storiesDto: StoriesDto
        val flowableList = storiesDao.getAllStories()
        return flowableList.map {
            it.map { storyEntity ->
                storiesDto = storyEntity.toStoriesDto()
                storiesDto
            }
        }
    }
    // get by id from  room db with completable observer

    fun getStory(_id: Long): Flowable<StoriesDto> {
        lateinit var storiesDto: StoriesDto
        val flowable = storiesDao.getStories(_id)
        return flowable.map {
            storiesDto = it.toStoriesDto()
            storiesDto
        }
    }

    // get all from  remote db with completable observer
    fun getStoriesRemote(characterId: Long): Flowable<List<StoriesDto>> {
        lateinit var storiesDto: StoriesDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getStories(characterId = characterId)
        return flowableList.map {
            Log.wtf("E123", it.toString())

            it.data.results.map { stories ->
                storiesDto = stories.toStoriesDto()
                storiesDao.insert(storiesDto.toStoriesEntity())
                storiesDto
            }
        }
    }

}