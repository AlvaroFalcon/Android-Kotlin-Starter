package com.frostfel.starterproject.extension

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Mario Peñate Fariñas on 8/2/18.
 */

class SharedPreferencesExtension {
    val DOWNLOADED_DATA = "DOWNLOADED_DATA"
    companion object {
        fun getPreferenceManager(context : Context) : SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}
fun SharedPreferences.setBool(key : String, value : Boolean){
    val editor = this.edit()
    editor.putBoolean(key, value)
    editor.apply()
    editor.commit()
}

fun SharedPreferences.getBool(key : String) : Boolean{
    return this.getBoolean(key, false)
}

fun SharedPreferences.setString(key: String , value : String) {

    val editor = this.edit()
    editor.putString(key, value)
    editor.apply()
    editor.commit()
}

fun SharedPreferences.getStringValue(key : String) : String? {
    return getString(key, null)
}




