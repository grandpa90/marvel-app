package com.grandpa.marvelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandpa.marvelapp.model.dto.CharacterDto
import com.grandpa.marvelapp.repositories.CharacterRepository
import com.grandpa.marvelapp.roomdb.entities.CharacterEntity
import io.reactivex.Flowable

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    var characterRepository: CharacterRepository

    init {
        characterRepository = CharacterRepository(application)
    }

    fun insertCharacter(characterDto: CharacterDto) {
        characterRepository.insertCharacter(
            CharacterEntity(
                characterDto._id,
                characterDto.name,
                characterDto.description,
                characterDto.thumbnail
            )
        )
    }

    fun updateCharacter(characterDto: CharacterDto) {
        characterRepository.updateCharacer(
            CharacterEntity(
                characterDto._id,
                characterDto.name,
                characterDto.description,
                characterDto.thumbnail
            )
        )
    }

    fun deleteAllCharacters() {
        characterRepository.deleteCharacters()
    }

    fun deleteCharacter(characterDto: CharacterDto) {
        characterRepository.deleteCharacter(characterDto._id)
    }

    fun getCharacter(_id: Long): Flowable<CharacterDto> {
        return characterRepository.getCharacter(_id)
    }

    fun getCharacters(): Flowable<List<CharacterDto>> {
        return characterRepository.getCharacters()
    }

    fun getCharactersRemote(): Flowable<List<CharacterDto>> {
        return characterRepository.getCharactersRemote()
    }

}