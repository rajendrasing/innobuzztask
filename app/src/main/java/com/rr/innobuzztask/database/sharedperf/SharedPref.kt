package com.rr.innobuzztask.database.sharedperf

import android.content.Context
import android.content.SharedPreferences
import com.rr.innobuzztask.application.MyApp.Companion.appContext
import com.rr.innobuzztask.database.sharedperf.SharedPrefConstant.SHARED_PREF_NAME


object SharedPref {

    private var editor: SharedPreferences.Editor? = null



    private fun getSharedPref(): SharedPreferences?{
        return appContext?.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
    }

    private fun getEditer() : SharedPreferences.Editor?{
        if (editor==null){
            editor= getSharedPref()?.edit()
        }
        return editor
    }

    fun getString(key :String,defaultValue : String = "No Data") = getSharedPref()?.getString(key,defaultValue)?:defaultValue
    fun getInt(key :String,defaultValue: Int = 0) = getSharedPref()?.getInt(key,defaultValue)?:defaultValue
    fun getFloat(key: String,defaultValue: Float=0f) = getSharedPref()?.getFloat(key,defaultValue)?:defaultValue
    fun getLong(key: String,defaultValue: Long=0L) = getSharedPref()?.getLong(key,defaultValue)?:defaultValue
    fun getBoolean(key: String,defaultValue: Boolean=false) = getSharedPref()?.getBoolean(key,defaultValue)?:defaultValue

    fun setString(key: String,value : String){
        getEditer()?.let {
            it.putString(key,value)
            it.apply()
        }
    }

    fun setInt(key: String,value : Int){
        getEditer()?.let {
            it.putInt(key,value)
            it.apply()
        }
    }

    fun setFloat(key: String,value : Float){
        getEditer()?.let {
            it.putFloat(key,value)
            it.apply()
        }
    }

    fun setLong(key: String,value : Long){
        getEditer()?.let {
            it.putLong(key,value)
            it.apply()
        }
    }

    fun setBoolean(key: String,value : Boolean){
        getEditer()?.let {
            it.putBoolean(key,value)
            it.apply()
        }
    }

    fun clearAll() {
        getEditer()?.let {
            it.clear()
            it.apply()
        }
    }

    fun delete(key: String) {
        getEditer()?.let{
            it.remove(key)
            it.apply()
        }
    }


}