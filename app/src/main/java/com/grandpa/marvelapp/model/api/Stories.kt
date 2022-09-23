package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.StoriesDto

// modeling stories
data class Stories(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    @SerializedName("variantDescription")
    var description: String,
    var thumbnail: Thumbnail

) {
    // to dto function is used to map api model into (Data Transfer OBJECT)
    fun toStoriesDto(): StoriesDto {
        return StoriesDto(_id, title, description, (thumbnail.path + "." + thumbnail.extension))
    }
}