package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.StoriesDto


data class Stories(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail

) {
    fun toStoriesDto(): StoriesDto {
        return StoriesDto(_id, title, description, (thumbnail.path + thumbnail.extension))
    }
}