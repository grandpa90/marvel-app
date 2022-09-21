package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grandpa.marvelapp.model.dto.StoriesDto

// entities for room db

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
) {
    fun toStoriesDto(): StoriesDto {
        return StoriesDto(_id, title, description, thumbnail)
    }
}