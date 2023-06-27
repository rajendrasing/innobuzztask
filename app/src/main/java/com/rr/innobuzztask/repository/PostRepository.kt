package com.rr.innobuzztask.repository

import android.content.Context
import android.util.Log
import com.rr.innobuzztask.api.ApiService
import com.rr.innobuzztask.api.RetrofitHelper
import com.rr.innobuzztask.database.roomdatabase.InnobuzzDatabase
import com.rr.innobuzztask.model.response.PostResponse
import com.rr.innobuzztask.utils.NetworkUtils
import retrofit2.Response

class PostRepository (private val apiService: ApiService, private val innobuzzDatabase: InnobuzzDatabase, private val applicationContext: Context){

    companion object {
        private var instance: PostRepository? = null

        suspend fun getInstance(context: Context): PostRepository? {
            if (instance == null) {
                val appDatabase =  InnobuzzDatabase.getDatabase(context)
                val apiService = RetrofitHelper.getRetrofit()
                instance = PostRepository(apiService,appDatabase,context)
            }
            return instance
        }
    }

    suspend fun getPost():List<PostResponse>{
        var postList : List<PostResponse>? = null
        if (NetworkUtils.isInternetAvailable(applicationContext)){
            postList = apiService.getPost()
            innobuzzDatabase.postDao().addPost(postList)
        }else{
            postList = innobuzzDatabase.postDao().getPost()
        }
        return postList!!
    }




}