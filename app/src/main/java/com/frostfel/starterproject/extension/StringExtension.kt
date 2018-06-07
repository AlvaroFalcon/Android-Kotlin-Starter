package com.frostfel.starterproject.extension

import android.util.Base64
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by Alvaro on 1/2/18.
 */




    fun String.isValidEmail(): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(this).matches()
    }

    fun String.isValidPassword() : Boolean {
        return this.length >= 4
    }

    fun String.isValidPhone() : Boolean {
        var reg = "[0-9]+"
        return this.matches(reg.toRegex())
        //return reg.toRegex().matches(this)
    }

    fun String.encrypt() : String {
        return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
    }

    fun String.decrypt() : String {
        return String(Base64.decode(this, Base64.DEFAULT))
    }

    fun String.getFilenameFromUrl(): String{
        return this.substring(this.lastIndexOf("/"),this.length)
    }





