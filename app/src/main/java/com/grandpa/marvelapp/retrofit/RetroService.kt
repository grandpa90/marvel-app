package com.grandpa.marvelapp.retrofit

import retrofit2.http.GET

interface RetroService {

    @GET("characters")
    fun getCharacters()

}