package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.EventsDto
// modeling events
data class Events(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail
) {
    // convert event model api to event dto (Data Transfer OBJECT)
    fun eventsToDto(): EventsDto {
        return EventsDto(_id, title, description, (thumbnail.path + "." + thumbnail.path))
    }
}