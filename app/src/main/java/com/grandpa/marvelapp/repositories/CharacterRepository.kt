package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.CharacterDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.CharacterDao
import com.grandpa.marvelapp.roomdb.entities.CharacterEntity
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharacterRepository(application: Application) {

    var characterDao: CharacterDao

    init {
        val db = MarvelRoomDB.getDatabase(application)
        characterDao = db.CharacterDao()
    }

    // insert
    fun insertCharacter(characterDto: CharacterDto) {
        Completable.create {
            characterDao.insert(
                CharacterEntity(
                    characterDto._id, characterDto.name,
                    characterDto.description, characterDto.thumbnail
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                    // disposable.dispose()
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    // update
    fun updateCharacter(characterDto: CharacterDto) {
        Completable.create {
            characterDao.update(
                CharacterEntity(
                    characterDto._id, characterDto.name,
                    characterDto.description, characterDto.thumbnail
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

    fun deleteCharacters() {
        Completable.create {
            characterDao.deleteAll()
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

    fun deleteCharacter(_id: Long) {
        Completable.create {
            characterDao.deleteCharacter(_id)
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

    fun getCharacter(_id: Long): Flowable<CharacterDto> {
        lateinit var characterDto: CharacterDto
        val flowableEntity = characterDao.getCharacter(_id = _id)
        return flowableEntity.map {
            characterDto = CharacterDto(it._id, it.name, it.description, it.thumbnail)
            characterDto
        }
    }

    fun getCharacters(): Flowable<List<CharacterDto>> {
        lateinit var characterDto: CharacterDto
        val flowableEntityList = characterDao.getAllCharacter()
        return flowableEntityList.map { listEntity ->
            listEntity.map {
                characterDao.insert(it)
                characterDto = CharacterDto(it._id, it.name, it.description, it.thumbnail)
                characterDto
            }
        }
    }

    // online
    fun getCharactersRemote(): Flowable<List<CharacterDto>> {
        lateinit var characterDto: CharacterDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getCharacters()
        return flowableList.map {
            it.data.result
                .map { character ->
                    characterDto = character.characterToDto()
                    // here we need to insert
                    characterDto
                }
        }
    }

}
