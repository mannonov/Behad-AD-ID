package com.behad.adid

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import androidx.core.content.ContextCompat

fun String.getOnlyDigits(): Long {
    var s = ""
    forEach { if (it.isDigit()) s += it }
    return (s.ifEmpty { "0" }).toLong()
}

fun Context.hasPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        true
    } else {
        ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
    }
}

fun checkPermission(context: Context): Boolean {
    return if (SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        val result =
            ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE)
        val result1 =
            ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE)
        result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }
}
