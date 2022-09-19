package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.ComicsDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.ComicsDao
import com.grandpa.marvelapp.roomdb.entities.ComicsEntity
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ComisRepository(application: Application) {

    var comicsDao: ComicsDao
    var db: MarvelRoomDB

    init {
        db = MarvelRoomDB.getDatabase(application)
        comicsDao = db.ComicsDao()
    }


    fun insertComics(comicsDto: ComicsDto) {
        // var disposable = CompositeDisposable()
        Completable.create {
            comicsDao.insert(
                comicsEntity = ComicsEntity(
                    comicsDto.id, comicsDto.title, comicsDto.description, comicsDto.thumbnail
                )
            )
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


    fun updateComics(comicsDto: ComicsDto) {
        Completable.create {
            comicsDao.insert(
                comicsEntity = ComicsEntity(
                    comicsDto.id, comicsDto.title,
                    comicsDto.description, comicsDto.thumbnail
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


    fun deleteAllComics() {
        Completable.create {
            comicsDao.deleteAllComics()
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


    fun deleteComics(_id: Long) {
        Completable.create {
            comicsDao.deleteComics(_id = _id)
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


    fun getComics(_id: Long): Flowable<ComicsDto> {
        val flowableList = comicsDao.getComics(_id = _id)
        lateinit var comicsDto: ComicsDto
        return flowableList.map {
            comicsDto = it.toComicsDto()
            comicsDto
        }
    }


    fun getComicsRemote(characterId: Long): Flowable<List<ComicsDto>> {
        lateinit var comicsDto: ComicsDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getComics(characterId = characterId)
        return flowableList.map {
            it.data.result.map { comics ->
                comicsDao.insert(
                    ComicsEntity(
                        comicsDto.id, comicsDto.title,
                        comicsDto.description, comicsDto.thumbnail
                    )
                )
                comicsDto = comics.toComicsDto()
                comicsDto
            }
        }
    }

}