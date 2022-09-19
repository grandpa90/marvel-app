package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.ComicsDto

data class Comics(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail
) {
    fun toComicsDto(): ComicsDto {
        return ComicsDto(_id, title, description, (thumbnail.path + thumbnail.extension))
    }
}