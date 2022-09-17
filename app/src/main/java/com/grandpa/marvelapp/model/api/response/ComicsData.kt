package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Comics
import io.reactivex.Flowable

data class ComicsData(
    var ofset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: Flowable<List<Comics>>
)