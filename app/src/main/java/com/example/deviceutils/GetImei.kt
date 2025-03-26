package com.example.deviceutils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.annotation.RequiresApi

class GetImei {

    protected lateinit var userPreferences: UserPreferences

    companion object {
        var IMEINumber: String = ""

        @JvmName("getImeiNumber1")
        fun getImeiNumber(): String {
            return IMEINumber
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getImei(context: Context) {
        var cihazMarkasi = getDeviceName()
        userPreferences = UserPreferences(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (cihazMarkasi != null) {
                if (cihazMarkasi.contains("Urovo"))
                {
                    IMEINumber = "111111111111111"
                }
//                else if (cihazMarkasi.contains("Unitech"))
//                {
//                    var deviceInfoCtrl = DeviceInfoCtrl(context)
//                    var bundle : Bundle = deviceInfoCtrl.getIMEI()
//                    IMEINumber = bundle.getString("getImei").toString()
//                    var imei = bundle.getString("getImei")
//                    IMEINumber = bundle.toString()
//                }
                else
                    IMEINumber = "222222222222222"
            }
        } else {
            val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            IMEINumber = manager.getImei()
        }
    }

    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else capitalize(manufacturer) + " " + model
    }

    private fun capitalize(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true
        val phrase = StringBuilder()
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c))
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase.append(c)
        }
        return phrase.toString()
    }

}
