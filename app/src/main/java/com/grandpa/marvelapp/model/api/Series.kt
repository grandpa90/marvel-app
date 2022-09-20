package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.SeriesDto

data class Series(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail
) {
    fun toSeriesDto(): SeriesDto {
        return SeriesDto(_id, title, description, (thumbnail.path + thumbnail.extension))
    }
}