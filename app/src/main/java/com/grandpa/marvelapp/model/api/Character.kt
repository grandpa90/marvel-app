package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    var _id: Long,
    @SerializedName("name")
    var mcName: String,
    @SerializedName("description")
    var mcDescription: String,
    var thumbnail: Thumbnail
)