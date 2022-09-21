package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Stories

// modeling stories data of the api
data class StoriesData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: List<Stories>
)