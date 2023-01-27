package com.behad.adid

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdvertisingInfo(context: Context) {

    private val adInfo = AdvertisingIdClient(context)

    suspend fun getAdvertisingId(): String? =
        withContext(Dispatchers.IO) {
            // Connect with start(), disconnect with zza()
            adInfo.start()
            val adIdInfo = adInfo.info
            adInfo.zza()
            adIdInfo.id
        }
}
