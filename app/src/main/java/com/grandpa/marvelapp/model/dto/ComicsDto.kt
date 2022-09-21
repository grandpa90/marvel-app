package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.ComicsEntity

class ComicsDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    fun toComicsEntity(): ComicsEntity {
        return ComicsEntity(_id, title, description, thumbnail)
    }
}