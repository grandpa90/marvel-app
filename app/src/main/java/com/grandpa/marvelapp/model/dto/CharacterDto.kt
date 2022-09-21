package com.grandpa.marvelapp.model.dto

import com.grandpa.marvelapp.roomdb.entities.CharacterEntity

// character (Data Transfer OBJECT) class
class CharacterDto(
    var _id: Long,
    var name: String,
    var description: String,
    var thumbnail: String
) {
    // to map the dto class into entity for room db
    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(_id, name, description, thumbnail)
    }
}