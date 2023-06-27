package com.rr.innobuzztask.utils

import com.rr.innobuzztask.database.sharedperf.SharedPref
import com.rr.innobuzztask.database.sharedperf.SharedPrefConstant

object AppFunctions {

    fun getVerificationToken() = SharedPref.getString(key = SharedPrefConstant.TOKEN) ?: ""
}