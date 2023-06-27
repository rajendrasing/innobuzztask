package com.rr.innobuzztask.utils

import android.util.Patterns

object Validation {

    fun isValidEmail(email:String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()?:false

    fun isValidPassword(password : String) :Boolean{
        if (password.length>=8) return true
        return false
    }



    fun isValidOtp(otp : String) :Boolean{
        if (otp.length<4) return true
        return false
    }

}