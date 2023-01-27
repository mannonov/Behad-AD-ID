package com.behad.adid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BehadADID.getAdId(
            this,
            object : BehadCallBack {
                override fun onSuccess(string: String?) {
                    Log.d("mainActBehad", "onSuccess: $string")
                }

                override fun onFailure(e: Throwable) {
                    Log.d("mainActBehad", "onFailure: $e")
                }
            }
        )
    }
}
