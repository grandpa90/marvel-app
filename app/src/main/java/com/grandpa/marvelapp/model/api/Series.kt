package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.SeriesDto
// modeling series
data class Series(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    //var description: String,
    var thumbnail: Thumbnail
) {
    // convert series api model into series dto model (Data Transfer OBJECT)
    fun toSeriesDto(): SeriesDto {
        return SeriesDto(_id, title, "", (thumbnail.path + "." + thumbnail.extension))
    }
}