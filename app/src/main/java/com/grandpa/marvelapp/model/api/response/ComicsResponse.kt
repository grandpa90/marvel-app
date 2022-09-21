package com.grandpa.marvelapp.model.api.response

import com.google.gson.annotations.SerializedName

// modeling comics response of the api
data class ComicsResponse(
    var code: Int,
    var status: String,
    @SerializedName("etag")
    var eTag: String,
    var data: ComicsData
)