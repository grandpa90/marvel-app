package com.grandpa.marvelapp.repositories

import android.app.Application
import com.grandpa.marvelapp.model.dto.CharacterDto
import com.grandpa.marvelapp.retrofit.RetroInstance
import com.grandpa.marvelapp.retrofit.RetroService
import com.grandpa.marvelapp.roomdb.MarvelRoomDB
import com.grandpa.marvelapp.roomdb.daos.CharacterDao
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// repository for manipulating the data ...
class CharacterRepository(application: Application) {

    // declare  the dao interface
    var characterDao: CharacterDao

    init {
        // initialize the room db
        val db = MarvelRoomDB.getDatabase(application)
        // initialize the dao interface
        characterDao = db.CharacterDao()
    }

    // insert into room db with completable observer
    fun insertCharacter(characterDto: CharacterDto) {
        Completable.create {
            characterDao.insert(characterDto.toCharacterEntity()) // calling the interface to insert dto after mapping it to entity
            it.onComplete() // signal to complete the stream
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

    // update function with completable observer
    fun updateCharacter(characterDto: CharacterDto) {
        Completable.create {
            characterDao.update(characterDto.toCharacterEntity()) // insert into room db the dto after mapping into entity
            it.onComplete() // signal to complete the stream
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

    // delete all characters from room db with completable observer
    fun deleteCharacters() {
        Completable.create {
            characterDao.deleteAll() // calling the interface to delete
            it.onComplete() // signal the stream to be completed after the deletion
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

    // delete character with id with completable observer
    fun deleteCharacter(_id: Long) {
        Completable.create {
            characterDao.deleteCharacter(_id)  // calling the interface to delete
            it.onComplete() // signal observer to completed after the deletion is performed
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

    // get character by id with flowable observer
    fun getCharacter(_id: Long): Flowable<CharacterDto> {
        lateinit var characterDto: CharacterDto
        val flowableEntity = characterDao.getCharacter(_id = _id)
        // mapping entity into dto
        return flowableEntity.map {
            characterDto = it.toCharacterDto()
            characterDto
        }
    }

    // get all characters with flowable observer
    fun getCharacters(): Flowable<List<CharacterDto>> {
        lateinit var characterDto: CharacterDto
        val flowableEntityList = characterDao.getAllCharacter()
        // mapping entities into dto
        return flowableEntityList.map { listEntity ->
            listEntity.map {
                characterDto = CharacterDto(it._id, it.name, it.description, it.thumbnail)
                characterDto
            }
        }
    }

    // getting all remote character and insert them locally
    fun getCharactersRemote(): Flowable<List<CharacterDto>> {
        lateinit var characterDto: CharacterDto
        val retroInstance = RetroInstance.getRxRetrofitInstance().create(RetroService::class.java)
        val flowableList = retroInstance.getCharacters()
        return flowableList.map {
            it.data.result
                .map { character ->
                    characterDto = character.characterToDto()
                    // here we need to insert
                    // mapping dto to entity to insert into room db
                    characterDao.insert(characterDto.toCharacterEntity())
                    characterDto
                }
        }
    }

}
