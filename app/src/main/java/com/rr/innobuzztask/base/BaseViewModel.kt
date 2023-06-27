package com.rr.innobuzztask.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val error = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = error

    val showProgress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean>
        get() = showProgress


}