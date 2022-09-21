package com.grandpa.marvelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandpa.marvelapp.model.dto.SeriesDto
import com.grandpa.marvelapp.repositories.SeriesRepository
import io.reactivex.Flowable

class SeriesViewModel(application: Application) : AndroidViewModel(application) {
    var seriesRepository: SeriesRepository

    init {
        seriesRepository = SeriesRepository(application)
    }

    fun insertSeries(seriesDto: SeriesDto) {
        seriesRepository.insertSeries(seriesDto)
    }

    fun updateSeries(seriesDto: SeriesDto) {
        seriesRepository.updateSeries(seriesDto)
    }

    fun deleteSeries(_id: Long) {
        seriesRepository.deleteSeries(_id)
    }

    fun deleteAllSeries() {
        seriesRepository.deleteAllSeries()
    }

    fun getAllSeries(): Flowable<List<SeriesDto>> {
        return seriesRepository.getAllSeries()
    }

    fun getSeries(_id: Long): Flowable<SeriesDto> {
        return seriesRepository.getSeries(_id)
    }

    fun getSeriesRemote(characterId: Long): Flowable<List<SeriesDto>> {
        return seriesRepository.getSeriesRemote(characterId)
    }

}