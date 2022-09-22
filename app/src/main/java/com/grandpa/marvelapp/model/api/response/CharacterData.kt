package com.grandpa.marvelapp.model.api.response

import com.grandpa.marvelapp.model.api.Character

// modeling of the character data of the api
data class CharacterData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: List<Character>
)