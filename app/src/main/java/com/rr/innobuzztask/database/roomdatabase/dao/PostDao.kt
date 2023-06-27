package com.rr.innobuzztask.database.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rr.innobuzztask.model.response.PostResponse


@Dao
interface PostDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPost(post : List<PostResponse>)


    @Query("SELECT * FROM post")
    suspend fun getPost() : List<PostResponse>
}