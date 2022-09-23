package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.ComicsDto
// modeling comics
data class Comics(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    @SerializedName("variantDescription")
    var description: String,
    var thumbnail: Thumbnail
) {
    // convert comics model into comics dto model (Data Transfer OBJECT)
    fun toComicsDto(): ComicsDto {
        return ComicsDto(_id, title, description, (thumbnail.path + "." + thumbnail.extension))
    }
}