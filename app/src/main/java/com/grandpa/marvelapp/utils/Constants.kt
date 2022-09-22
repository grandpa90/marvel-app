package com.grandpa.marvelapp.utils

class Constants {


    // constants for room db

    object ROOM_DB {
        const val ROOM_DB_NAME = "MarvelRoomDB" // room db name
    }


    /*
    these key will be used on each call of the API

     */
    object API_KEY {
        const val PUBLIC_KEY = "26fe45366be9a9a1c5c3374e19f144e6" // public key for marvel api
        const val PRIVATE_KEY =
            "a2e22b0ea13a8cbc97b32bd3471f8fbcd807e14c" // private key for marvel api
    }

    /*
    base api url
     */
    object DEV_API {
        const val BASE_API =
            "https://gateway.marvel.com/v1/public/" // bas url for retrofit of marvel apis
    }

    /*
    status code of the server
    the most important ones
     */
    object STATUS_CODE {
        const val STATUS_OK = 200
        const val NOT_FOUND = 404
        const val UNAUTHORIZED = 401
    }

}