package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stories_entity")
class StoriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "stories_id")
    var _id: Long,
    @ColumnInfo(name = "stories_title")
    var title: String,
    @ColumnInfo(name = "stories_description")
    var description: String,
    @ColumnInfo(name = "stories_thumbnail")
    var thumbnail: String
)