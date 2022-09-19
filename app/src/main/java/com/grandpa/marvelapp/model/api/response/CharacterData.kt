package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Character

data class CharacterData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var result: List<Character>
)