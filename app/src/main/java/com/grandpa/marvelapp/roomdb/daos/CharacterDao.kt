package com.grandpa.marvelapp.roomdb.daos

import androidx.room.*
import com.grandpa.marvelapp.roomdb.entities.CharacterEntity
import io.reactivex.Flowable

/*
* dao for insert update delete and select
* for room db
* */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(character: CharacterEntity)

    @Query("DELETE FROM CHARACTERENTITY WHERE character_id= :_id")
    fun deleteCharacter(_id: Long)

    @Query("DELETE  from CHARACTERENTITY")
    fun deleteAll()

    @Query("SELECT * FROM CHARACTERENTITY")
    fun getAllCharacter(): Flowable<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity where character_id= :_id")
    fun getCharacter(_id: Long): Flowable<CharacterEntity>
}