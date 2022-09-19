package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.CharacterEntity
import io.reactivex.Flowable


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterEntity)

    @Update
    fun update(character: CharacterEntity)

    @Query("DELETE  from CHARACTERENTITY")
    fun deleteAll()

    @Query("SELECT * FROM CHARACTERENTITY")
    fun getAllCharacter(): Flowable<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity where character_id= :_id")
    fun getCharacter(_id: Int): Flowable<CharacterEntity>
}