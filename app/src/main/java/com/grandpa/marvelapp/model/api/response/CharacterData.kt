package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Character
import io.reactivex.Flowable

data class CharacterData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: Flowable<List<Character>>
)