package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.EventsEntity
// events (Data Transfer OBJECT)
class EventsDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: String
) {
    // convert dto class into entity for room db
    fun toEventsEntity(): EventsEntity {
        return EventsEntity(_id, title, description, thumbnail)
    }
}