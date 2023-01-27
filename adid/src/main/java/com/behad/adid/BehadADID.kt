package com.behad.adid

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object BehadADID {

    private var advertisingInfo: AdvertisingInfo? = null

    private suspend fun applyDeviceId(context: Context): Result<String?> {
        return try {
            advertisingInfo = AdvertisingInfo(context)
            Result.success(advertisingInfo?.getAdvertisingId())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getAdId(context: Context, callBack: BehadCallBack) {
        CoroutineScope(Dispatchers.IO).launch {
            applyDeviceId(context).onSuccess {
                withContext(Dispatchers.Main) {
                    callBack.onSuccess(it)
                }
            }
                .onFailure {
                    withContext(Dispatchers.Main) {
                        callBack.onFailure(it)
                    }
                }
        }
    }
}
