package com.rr.innobuzztask.ui.fragment.post

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rr.innobuzztask.base.BaseAndroidViewModel
import com.rr.innobuzztask.model.response.PostResponse
import com.rr.innobuzztask.repository.PostRepository
import com.rr.innobuzztask.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

class PostViewModel(application: Application) :BaseAndroidViewModel(application){

    private val postLiveData = MutableLiveData<List<PostResponse>>()

    val post : LiveData<List<PostResponse>>
        get() = postLiveData


    init {
        GetPost()
    }


     fun GetPost() {
            viewModelScope.launch {
                showProgress.postValue(true)
                val result : List<PostResponse>?
                withContext(Dispatchers.IO){
                    result = PostRepository.getInstance(context)?.getPost()
                }

                withContext(Dispatchers.Main){
                    showProgress.postValue(false)
                    if (result!=null){
                        postLiveData.postValue(result!!)
                    }else{
                        error.postValue("Something Went Wrong Please Check Network")
                    }
                }
            }
        }
}