package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.CharacterEntity

class CharacterDto(
    var _id: Long,
    var name: String,
    var description: String,
    var thumbnail: String
) {
    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(_id, name, description, thumbnail)
    }
}