package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.EventsEntity

class EventsDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    fun toEventsEntity(): EventsEntity {
        return EventsEntity(_id, title, description, thumbnail)
    }
}