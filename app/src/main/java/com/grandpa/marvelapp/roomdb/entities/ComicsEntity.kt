package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ComicsEntity")
class ComicsEntity(
    @PrimaryKey
    @ColumnInfo(name = "comics_id")
    var _id: Long,
    @ColumnInfo(name = "comics_title")
    var title: String,
    @ColumnInfo(name = "comics_description")
    var description: String,
    @ColumnInfo(name = "comics_thumbnail")
    var thumbnail: String
)



