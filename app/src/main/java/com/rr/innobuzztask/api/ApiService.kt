package com.rr.innobuzztask.api

import com.rr.innobuzztask.model.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<PostResponse>

}