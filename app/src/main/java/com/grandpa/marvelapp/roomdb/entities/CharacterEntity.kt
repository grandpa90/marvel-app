package com.grandpa.marvelapp.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grandpa.marvelapp.model.dto.CharacterDto
// entities for room db
@Entity(tableName = "CharacterEntity")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    var _id: Long,
    @ColumnInfo(name = "character_name")
    var name: String,
    @ColumnInfo(name = "character_description")
    var description: String,
    @ColumnInfo(name = "character_thumbnail")
    var thumbnail: String
) {
    // convert dto to entity
    fun fromDto(characterDto: CharacterDto): CharacterEntity {
        return CharacterEntity(
            _id = characterDto._id,
            name = characterDto.name,
            description = characterDto.description,
            thumbnail = characterDto.thumbnail
        )
    }

    // convert entity to dto
    fun toCharacterDto(): CharacterDto {
        return CharacterDto(
            _id = _id,
            name = name,
            description = description,
            thumbnail = thumbnail
        )
    }
}