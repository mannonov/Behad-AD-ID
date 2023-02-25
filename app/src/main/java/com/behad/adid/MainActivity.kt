package com.behad.adid

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && hasPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        ) {
            Log.d("mainActivPermis", "has a permission ${checkPermission(this)}")
        } else {
            Log.d("mainActivPermis", "has a else")
        }

        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                BehadADID.getAdId(
                    this,
                    object : BehadCallBack {
                        override fun onSuccess(string: String?) {
                            findViewById<TextView>(R.id.tv_text).text = string
                        }

                        override fun onFailure(e: Throwable) {
                            Log.d("mainActBehad", "onFailure: $e")
                        }
                    },
                )
            } else {
            }
        }.launch(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    }


}
