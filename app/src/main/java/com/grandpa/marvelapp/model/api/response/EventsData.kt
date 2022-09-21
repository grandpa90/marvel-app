package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Events

data class EventsData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: List<Events>
)