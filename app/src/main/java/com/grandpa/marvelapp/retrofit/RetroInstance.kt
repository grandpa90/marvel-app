package com.grandpa.marvelapp.retrofit

import com.grandpa.marvelapp.utils.Constants
import com.grandpa.marvelapp.utils.Constants.API_KEY.PRIVATE_KEY
import com.grandpa.marvelapp.utils.utils
import com.grandpa.marvelapp.utils.utils.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        /*
        create retrofit instance using gson & RxJava2

         */

        fun getRxRetrofitInstance(): Retrofit {
            val retrofit = Retrofit.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(loggingInterceptor)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", Constants.API_KEY.PUBLIC_KEY)
                    .addQueryParameter("ts", utils.getTimeStamp())
                    .addQueryParameter(
                        "hash",
                        "${utils.getTimeStamp()}$PRIVATE_KEY${Constants.API_KEY.PUBLIC_KEY}".md5()
                    )
                    .build()
                chain.proceed(original.newBuilder().url(url = url).build())
            }

            retrofit.client(httpClient.build())
            retrofit.baseUrl(Constants.DEV_API.BASE_API)
            retrofit.addConverterFactory(GsonConverterFactory.create())
            retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            return retrofit.build()
        }


    }
}