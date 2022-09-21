package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.ComicsEntity

// comics (Data Transfer OBJECT)
class ComicsDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    // convert comics dto into entity for room db
    fun toComicsEntity(): ComicsEntity {
        return ComicsEntity(_id, title, description, thumbnail)
    }
}