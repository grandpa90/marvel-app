package com.grandpa.marvelapp.retrofit

import com.grandpa.marvelapp.model.api.response.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {

    @GET("characters")
    fun getCharacters(): Flowable<CharacterResponse>

    @GET("characters/{characterId}/comics")
    fun getComics(@Path("characterId") characterId: Long): Flowable<ComicsResponse>

    @GET("character/{characterId}/stories")
    fun getStories(@Path("characterId") characterId: Long): Flowable<StoriesResponse>

    @GET("character/{characterId}/events")
    fun getEvents(@Path("characterId") characterId: Long): Flowable<EventsResponse>

    @GET("character/{characterId}/series")
    fun getSeries(@Path("characterId") characterId: Long): Flowable<SeriesResponse>


}