package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Comics

data class ComicsData(
    var ofset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: List<Comics>
)