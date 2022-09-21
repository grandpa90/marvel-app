package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.SeriesEntity
// series (Data Transfer OBJECT)
class SeriesDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    // convert series dto into entity for room db
    fun toSeriesEntity(): SeriesEntity {
        return SeriesEntity(_id, title, description, thumbnail)
    }
}