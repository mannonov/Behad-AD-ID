package com.behad.adid

import android.content.SharedPreferences

private const val ADID_KEY = "adid_key"
const val EMPTY_ADID = "empty"

class SharedPrefAdId(private val sharedPreferences: SharedPreferences) {

    var adID: String
        get() = sharedPreferences.getString(ADID_KEY, EMPTY_ADID) ?: EMPTY_ADID
        set(value) {
            sharedPreferences.edit().putString(ADID_KEY, value).apply()
        }
}
