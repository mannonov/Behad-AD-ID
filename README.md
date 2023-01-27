# Behad-AD-ID
This library provides AD-ID
<h3>Gradle implementation</h3>

            implementation 'com.github.mannonov:Behad-AD-ID:0.0.1'
in settings.gradle
            maven { url 'https://jitpack.io' }

<h3>Usage</h3>

       BehadADID.getAdId(
            context,
            object : BehadCallBack {
                override fun onFailure(e: Throwable) {
                    Log.d("ADID", "$e")
                }

                override fun onSuccess(adId: String?) {
                    Log.d("ADID", "siuu id $string")
                }
            }
        )
