package com.grandpa.marvelapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.security.NoSuchAlgorithmException
import java.util.*

object utils {

    /*
    we are extanding string class to this function to digest timestamp, private key
    & public key
     */
    fun String.md5(): String {
        try {
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(toByteArray())
            val messageDigest = digest.digest()
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }


    /* getting time stamp in string using utc ...
        will be called with every api
     */
    fun getTimeStamp(): String {
        return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    }

    /*
     temporary glid function for downloading icon of the char
     */
    fun ImageView.load(url: String) {
        Glide.with(context)
            .load(url)
            .into(this)
    }


}