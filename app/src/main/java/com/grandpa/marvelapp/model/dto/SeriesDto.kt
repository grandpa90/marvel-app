package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.SeriesEntity

class SeriesDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    fun toSeriesEntity(): SeriesEntity {
        return SeriesEntity(_id, title, description, thumbnail)
    }
}