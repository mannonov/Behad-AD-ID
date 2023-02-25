package com.behad.adid

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

object BehadADID {
    private const val SHARED_PREFS_NAME = "behad_adid_shared"
    private var advertisingInfo: AdvertisingInfo? = null
    private var behadSharedPrefAdId: SharedPrefAdId? = null
    private val TAG = "adIdTag"
    private var callBack: BehadCallBack? = null
    private suspend fun applyDeviceId(context: Context): Result<String?> {
        return try {
            advertisingInfo = AdvertisingInfo(context)
            Result.success(advertisingInfo?.getAdvertisingId())
        } catch (e: Exception) {
            Log.d(TAG, "applyDeviceId: $e")
            Result.failure(e)
        }
    }

    fun getAdId(activity: AppCompatActivity, callBack: BehadCallBack) {
        this.callBack = callBack
        behadSharedPrefAdId =
            SharedPrefAdId(activity.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE))
        if (behadSharedPrefAdId?.adID != EMPTY_ADID) {
            Log.d(TAG, "getAdId: shared pref is not empty ${behadSharedPrefAdId?.adID}")
            callBack.onSuccess(behadSharedPrefAdId?.adID)
            return
        }
        getAdIdT(activity)
    }

    private fun getAdIdT(activity: AppCompatActivity) {
        CoroutineScope(Dispatchers.IO).launch {
            applyDeviceId(activity).onSuccess { adId ->
                "00000-00000-00000-0000"?.let { it -> handleAdId(activity, it) }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    callBack?.onSuccess(randomUUID)
                    behadSharedPrefAdId?.adID = randomUUID
                    requestToStorage(activity, permissionCallBackForSave)
                }
            }
        }
    }

    private val permissionCallBackForSave: RequestCallBack = object : RequestCallBack {
        override fun onGranted(context: Context) {
            when (val text = readTxtFile()) {
                "" -> {
                    callBack?.onSuccess(randomUUID)
                    behadSharedPrefAdId?.adID = randomUUID
                    saveAdId(randomUUID)
                }
                else -> behadSharedPrefAdId?.adID = text
            }
        }

        override fun onDenied() {
            return
        }
    }

    private suspend fun handleAdId(
        activity: AppCompatActivity,
        string: String,
    ) {
        if (string.contains("000")) {
            checkForZero(activity)
        } else {
            withContext(Dispatchers.Main) {
                Log.d(TAG, "getAdId: adId successful get")
                callBack?.onSuccess(string)
                behadSharedPrefAdId?.adID = string
            }
        }
    }

    private suspend fun checkForZero(activity: AppCompatActivity) {
        withContext(Dispatchers.Main) {
            requestToStorage(activity, permissionCallBackForSave)
        }
    }

    private val randomUUID by lazy { UUID.randomUUID().toString().uppercase() }
}
