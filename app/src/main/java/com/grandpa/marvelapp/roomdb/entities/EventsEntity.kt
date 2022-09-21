package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grandpa.marvelapp.model.dto.EventsDto

// entities for room db

@Entity(tableName = "events_entity")
class EventsEntity(
    @PrimaryKey
    @ColumnInfo(name = "events_id")
    var _id: Long,
    @ColumnInfo(name = "event_title")
    var title: String,
    @ColumnInfo(name = "event_description")
    var description: String,
    @ColumnInfo(name = "event_thumbnail")
    var thumbnail: String
) {

    fun toEventsDto(): EventsDto {
        return EventsDto(_id, title, description, thumbnail)
    }
}



