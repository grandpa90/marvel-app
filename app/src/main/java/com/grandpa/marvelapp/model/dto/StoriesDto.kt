package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.StoriesEntity

class StoriesDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String

) {
    fun toStoriesEntity(): StoriesEntity {
        return StoriesEntity(_id, title, description, thumbnail)
    }
}