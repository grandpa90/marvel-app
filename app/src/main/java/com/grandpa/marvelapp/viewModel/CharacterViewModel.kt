package com.grandpa.marvelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandpa.marvelapp.model.dto.CharacterDto
import com.grandpa.marvelapp.repositories.CharacterRepository
import io.reactivex.Flowable
// calling all function of the repo inside the view model
class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private var characterRepository: CharacterRepository

    init {
        characterRepository = CharacterRepository(application)
    }

    fun insertCharacter(characterDto: CharacterDto) {
        characterRepository.insertCharacter(characterDto)
    }

    fun updateCharacter(characterDto: CharacterDto) {
        characterRepository.updateCharacter(characterDto)
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