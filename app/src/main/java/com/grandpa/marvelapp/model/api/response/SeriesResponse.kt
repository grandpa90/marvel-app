package com.grandpa.marvelapp.model.api.response

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    var code: Int,
    var status: String,
    @SerializedName("etag")
    var eTag: String,
    var data: SeriesData
)