# Behad-AD-ID
This library provides AD-ID
<h3>Gradle implementation</h3>

in project gradle file

            implementation 'com.github.mannonov:Behad-AD-ID:0.0.2'
            
in settings.gradle
            
            maven { url 'https://jitpack.io' }

<h3>Usage</h3>

Kotlin:

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
        
        
Java:

            BehadADID.INSTANCE.getAdId(this, new BehadCallBack() {
            @Override
            public void onSuccess(@Nullable String s) {
                Log.d("ADID", "onSuccess: " + s);
            }

            @Override
            public void onFailure(@NonNull Throwable throwable) {
                Log.d("ADID", "onFailure: " + throwable);
            }
        });
