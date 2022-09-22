package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Comics

// modeling of comics data of the api
data class ComicsData(
    var ofset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: List<Comics>
)