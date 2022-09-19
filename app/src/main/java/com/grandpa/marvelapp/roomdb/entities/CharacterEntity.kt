package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEntity")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    var _id: Long,
    @ColumnInfo(name = "character_name")
    var name: String,
    @ColumnInfo(name = "character_description")
    var description: String,
    @ColumnInfo(name = "character_thumbnail")
    var thumbnail: String
)