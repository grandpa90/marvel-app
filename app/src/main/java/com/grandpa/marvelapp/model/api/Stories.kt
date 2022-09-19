package com.grandpa.marvelapp.model.api

import com.google.gson.annotations.SerializedName


data class Stories(
    @SerializedName("id")
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail

)