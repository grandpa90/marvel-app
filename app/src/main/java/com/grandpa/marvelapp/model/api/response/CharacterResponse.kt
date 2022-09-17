package com.grandpa.marvelapp.model.api.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    var status: String,
    @SerializedName("code")
    var responseCode: Int,
    @SerializedName("etag")
    var eTag: String,
    var data: CharacterData
)