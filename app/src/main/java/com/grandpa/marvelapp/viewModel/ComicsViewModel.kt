package com.grandpa.marvelapp.viewModel

import androidx.lifecycle.ViewModel
import com.grandpa.marvelapp.model.dto.ComicsDto
import com.grandpa.marvelapp.repositories.ComicsRepository
import io.reactivex.Flowable

// calling all function of the repo inside the view model

class ComicsViewModel : ViewModel() {
    private var comicsRepository: ComicsRepository = ComicsRepository()

    fun insertComics(comicsDto: ComicsDto) {
        comicsRepository.insertComics(comicsDto)
    }

    fun updateComics(comicsDto: ComicsDto) {
        comicsRepository.updateComics(comicsDto)
    }

    fun deleteAllComics() {
        comicsRepository.deleteAllComics()
    }

    fun deleteComics(_id: Long) {
        comicsRepository.deleteComics(_id = _id)
    }

    fun getAllComics(): Flowable<List<ComicsDto>> {
        return comicsRepository.getAllComics()
    }

    fun getComics(_id: Long): Flowable<ComicsDto> {
        return comicsRepository.getComics(_id = _id)
    }

    fun getComicsRemote(_id: Long): Flowable<List<ComicsDto>> {
        return comicsRepository.getComicsRemote(_id)
    }
}