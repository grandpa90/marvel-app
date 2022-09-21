package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.StoriesEntity
// stories (Data Transfer OBJECT)
class StoriesDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String

) {
    // convert dto class into entity for room db
    fun toStoriesEntity(): StoriesEntity {
        return StoriesEntity(_id, title, description, thumbnail)
    }
}