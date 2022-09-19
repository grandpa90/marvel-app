package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Series

data class SeriesData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: List<Series>
)