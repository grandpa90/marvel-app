package com.grandpa.marvelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandpa.marvelapp.model.dto.EventsDto
import com.grandpa.marvelapp.repositories.EventsRepository
import io.reactivex.Flowable

class EventsViewModel(application: Application) : AndroidViewModel(application) {

    var eventsRepository: EventsRepository

    init {
        eventsRepository = EventsRepository(application)
    }

    fun insertEvent(eventsDto: EventsDto) {
        eventsRepository.insertEvents(eventsDto)
    }

    fun updateEvent(eventsDto: EventsDto) {
        eventsRepository.updateEvents(eventsDto)
    }

    fun deleteEvent(_id: Long) {
        eventsRepository.deleteEvent(_id)
    }

    fun deleteEvents() {
        eventsRepository.deleteEvents()
    }

    fun getAllEvents(): Flowable<List<EventsDto>> {
        return eventsRepository.getAllEvents()
    }

    fun getEvent(_id: Long): Flowable<EventsDto> {
        return eventsRepository.getEvent(_id)
    }

    fun getEventsRemote(characterId: Long): Flowable<List<EventsDto>> {
        return eventsRepository.getEventsRemote(characterId)
    }

}