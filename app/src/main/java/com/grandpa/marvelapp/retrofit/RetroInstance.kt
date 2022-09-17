package com.grandpa.marvelapp.retrofit

import com.grandpa.marvelapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        /*
        create retrofit instance using gson & RxJava2
         */


        fun getRxRetrofitInstance(): Retrofit {


            return Retrofit.Builder()
                .baseUrl(Constants.DEV_API.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }


    }
}