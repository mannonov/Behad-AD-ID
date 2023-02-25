package com.behad.adid

import android.Manifest
import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter

const val DIRECTORY = "/behadapps/"
const val FILE_NAME = "configs.txt"

fun requestToStorage(activity: AppCompatActivity, callBack: RequestCallBack) {
    if (activity.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && activity.hasPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    ) {
        callBack.onGranted(activity)
        return
    }
    activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            callBack.onGranted(activity)
        } else {
            callBack.onDenied()
        }
    }.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}

@Synchronized
fun saveAdId(adID: String) {
    Log.d("fileTagAD", "saveAdId: function vody")
    val newTextFile =
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            DIRECTORY + FILE_NAME,
        )

    if (!newTextFile.exists()) {
        Log.d("fileTagAD", "saveAdId: file created")
        requireNotNull(newTextFile.parentFile).mkdirs()
        val fw = FileWriter(newTextFile, true)
        fw.use {
            Log.d("fileTagAD", "saveAdId: appended $adID")
            it.append(adID)
        }
    }
}

fun readTxtFile(): String {
    return try {
        val newTextFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            DIRECTORY + FILE_NAME,
        )
        val inputAsString = FileInputStream(newTextFile).bufferedReader().use { it.readText() }
        Log.d("fileTagAd", "readTxtFile: $newTextFile")
        inputAsString.ifBlank { "" }
    } catch (e: Exception) {
        Log.d("fileTagAD", "readTxtFile:  $e")
        ""
    }
}
