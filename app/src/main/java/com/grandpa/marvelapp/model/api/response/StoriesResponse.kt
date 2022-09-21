package com.grandpa.marvelapp.model.api.response

import com.google.gson.annotations.SerializedName

// modeling stories response of the api
class StoriesResponse(
    var code: Int,
    var status: String,
    @SerializedName("etag")
    var eTag: String,
    var data: StoriesData
)