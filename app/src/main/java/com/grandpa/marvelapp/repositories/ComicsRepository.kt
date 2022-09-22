package com.grandpa.marvelapp.repositories

import com.grandpa.marvelapp.model.dto.ComicsDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.ComicsDao
import com.grandpa.marvelapp.roomdb.entities.ComicsEntity
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// repository for manipulating online and ofline data

class ComicsRepository() {

    // declaring interface and room db
    var comicsDao: ComicsDao
    var db: MarvelRoomDB

    init {
        // initializing interface and room db
        db = MarvelRoomDB.getDatabase(context = context)
        comicsDao = db.ComicsDao()
    }


    // insert into room db  with completable observer
    fun insertComics(comicsDto: ComicsDto) {
        Completable.create {
            comicsDao.insert(comicsDto.toComicsEntity())
            it.onComplete() // signal the streamer
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    //   disposable = d as CompositeDisposable
                }

                override fun onComplete() {
                    //   disposable.dispose()
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    // update room db with completable observer
    fun updateComics(comicsDto: ComicsDto) {
        Completable.create {
            comicsDao.update(comicsDto.toComicsEntity())
            it.onComplete() // signal the steamer to be completed
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

    // delete all comics from room db
    fun deleteAllComics() {
        Completable.create {
            comicsDao.deleteAllComics()
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

    // delete comicss by id from roomdb  with completable observer
    fun deleteComics(_id: Long) {
        Completable.create {
            comicsDao.deleteComics(id = _id)
            it.onComplete() // signal the streamer
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

    // get all comics from room db with Flowable observer
    // mapping entities into dto to be presented into the view

    fun getAllComics(): Flowable<List<ComicsDto>> {
        val flowableList = comicsDao.getAllComics()
        lateinit var comicsDto: ComicsDto
        return flowableList.map {
            it.map { comicsEntity ->
                comicsDao.insert(comicsEntity)
                comicsDto = comicsEntity.toComicsDto()
                comicsDto
            }
        }
    }

    // get  comics by id  from room db with Flowable observer
    // mapping entities into dto to be presented into the view
    fun getComics(_id: Long): Flowable<ComicsDto> {
        val flowableList = comicsDao.getComics(id = _id)
        lateinit var comicsDto: ComicsDto
        return flowableList.map {
            comicsDto = it.toComicsDto()
            comicsDto
        }
    }

    // get all comics from remote  db with Flowable observer
    // mapping entities into dto to be presented into the view
    fun getComicsRemote(characterId: Long): Flowable<List<ComicsDto>> {
        lateinit var comicsDto: ComicsDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getComics(characterId = characterId)
        return flowableList.map {
            it.data.results.map { comics ->

                comicsDto = comics.toComicsDto()
                comicsDao.insert(
                    ComicsEntity(
                        comicsDto._id, comicsDto.title,
                        comicsDto.description, comicsDto.thumbnail
                    )
                )
                comicsDto
            }
        }
    }

}