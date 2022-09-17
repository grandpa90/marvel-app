package com.grandpa.marvelapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.security.NoSuchAlgorithmException
import java.util.*

class utils {


    // string digestion ...

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


    // getting time stamp in string using utc ...
    fun getTimeStam(): String {
        return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    }

    // glide for picture ...
    fun ImageView.load(url: String) {
        Glide.with(context)
            .load(url)
            .into(this)
    }


}