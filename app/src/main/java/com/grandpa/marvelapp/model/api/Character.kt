package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName
import com.grandpa.marvelapp.model.dto.CharacterDto

data class Character(
    @SerializedName("id")
    var _id: Long,
    @SerializedName("name")
    var mcName: String,
    @SerializedName("description")
    var mcDescription: String,
    var thumbnail: Thumbnail
) {
    fun characterToDto(): CharacterDto {
        return CharacterDto(
            _id = _id,
            name = mcName,
            description = mcDescription,
            thumbnail = (thumbnail.path + thumbnail.extension)
        )
    }
}