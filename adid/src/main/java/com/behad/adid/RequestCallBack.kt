package com.behad.adid

import android.content.Context

interface RequestCallBack {

    fun onGranted(context: Context)
    fun onDenied()
}
