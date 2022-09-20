package com.grandpa.marvelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandpa.marvelapp.model.dto.StoriesDto
import com.grandpa.marvelapp.repositories.StoriesRepository
import io.reactivex.Flowable

class StoriesViewModel(application: Application) : AndroidViewModel(application) {

    private var storiesRepository: StoriesRepository

    init {
        storiesRepository = StoriesRepository(application)
    }

    fun insertStory(storiesDto: StoriesDto) {
        storiesRepository.insertStory(storiesDto)
    }

    fun updateStory(storiesDto: StoriesDto) {
        storiesRepository.updateStory(storiesDto)
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