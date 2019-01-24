package com.example.dhruvisha.socialmedialogins

import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printHashKey(this)
    }
    fun printHashKey(pContext: Context) {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("abcd", "printHashKey() Hash Key: " + hashKey)
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("abcd", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("abcd", "printHashKey()", e)
        }

    }
}
