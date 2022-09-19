package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.model.api.Thumbnail

class StoriesDto(
    var _id: Long,
    var title: String,
    var description: String,
    var thumbnail: Thumbnail

)