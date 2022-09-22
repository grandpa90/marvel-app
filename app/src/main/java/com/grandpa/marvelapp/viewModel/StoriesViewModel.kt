package com.grandpa.marvelapp.viewModel
// calling all function of the repo inside the view model

import androidx.lifecycle.ViewModel
import com.grandpa.marvelapp.model.dto.StoriesDto
import com.grandpa.marvelapp.repositories.StoriesRepository
import io.reactivex.Flowable

class StoriesViewModel() : ViewModel() {

    private var storiesRepository: StoriesRepository = StoriesRepository()

    fun insertStory(storiesDto: StoriesDto) {
        storiesRepository.insertStory(storiesDto)
    }

    fun updateStory(storiesDto: StoriesDto) {
        storiesRepository.updateStory(storiesDto)
    }

    fun deleteAllStories() {
        storiesRepository.deleteAllStories()
    }

    fun deleteStories(_id: Long) {
        storiesRepository.deleteStories(_id)
    }

    fun getAllStories(): Flowable<List<StoriesDto>> {
        return storiesRepository.getStories()
    }

    fun getStory(_id: Long): Flowable<StoriesDto> {
        return storiesRepository.getStory(_id)
    }

    fun getStoriesRemote(characterId: Long): Flowable<List<StoriesDto>> {
        return storiesRepository.getStoriesRemote(characterId)
    }

}