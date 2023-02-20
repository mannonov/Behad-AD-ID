package com.behad.adid

import android.content.Context

private const val SHARED_PREFS_NAME = "behad_adid_shared"
private const val ADID_KEY = "adid_key"
const val EMPTY_ADID = "empty"

class SharedPrefAdId(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    var AdID: String
        get() = sharedPreferences.getString(ADID_KEY, EMPTY_ADID) ?: EMPTY_ADID
        set(value) {
            sharedPreferences.edit().putString(ADID_KEY, value).apply()
        }
}
