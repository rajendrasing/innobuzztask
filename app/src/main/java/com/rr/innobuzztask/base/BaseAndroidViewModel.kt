package com.rr.innobuzztask.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    val error = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = error

    val showProgress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean>
        get() = showProgress

    val context = getApplication<Application>().applicationContext
        get() = field




}