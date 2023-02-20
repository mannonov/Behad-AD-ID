package com.behad.adid

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        BehadADID.getAdId(
//            this,
//            object : BehadCallBack {
//                override fun onSuccess(string: String?) {
//                    findViewById<TextView>(R.id.tv_text).text = string
//                }
//
//                override fun onFailure(e: Throwable) {
//                    Log.d("mainActBehad", "onFailure: $e")
//                }
//            },
//        )

        val URL = "content://com.behad.adid"
        val data = Uri.parse(URL)
        val cursor: Cursor? =
            contentResolver.query(data, null, null, null, BehadADID.DEFAULT_SORT_ORDER)
        cursor?.moveToFirst()
        val adID = cursor?.getString(cursor.getColumnIndex(BehadADID.AD_ID))
        Log.d("yopta", "onCreate: $adID")
        findViewById<TextView>(R.id.tv_text).text = adID
    }
}
