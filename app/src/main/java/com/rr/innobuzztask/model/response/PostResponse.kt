package com.rr.innobuzztask.model.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "post")
data class PostResponse(
    val body: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
) : Serializable