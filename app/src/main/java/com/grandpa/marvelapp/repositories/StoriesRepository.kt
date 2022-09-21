package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.StoriesDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.StoriesDao
import com.grandpa.marvelapp.roomdb.entities.StoriesEntity
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StoriesRepository(application: Application) {
    private var storiesDao: StoriesDao
    private var db: MarvelRoomDB

    init {
        db = MarvelRoomDB.getDatabase(application)
        storiesDao = db.StoriesDao()
    }

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

    fun getStory(_id: Long): Flowable<StoriesDto> {
        lateinit var storiesDto: StoriesDto
        val flowable = storiesDao.getStories(_id)
        return flowable.map {
            storiesDto = it.toStoriesDto()
            storiesDto
        }
    }


    fun getStoriesRemote(characterId: Long): Flowable<List<StoriesDto>> {
        lateinit var storiesDto: StoriesDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getStories(characterId = characterId)
        return flowableList.map {
            it.data.result.map { stories ->
                storiesDto = stories.toStoriesDto()
                storiesDao.insert(storiesDto.toStoriesEntity())
                storiesDto
            }
        }
    }

}