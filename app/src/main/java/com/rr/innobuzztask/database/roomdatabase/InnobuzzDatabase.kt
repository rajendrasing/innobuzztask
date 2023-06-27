package com.rr.innobuzztask.database.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rr.innobuzztask.database.roomdatabase.dao.PostDao
import com.rr.innobuzztask.model.response.PostResponse


@Database(entities = [PostResponse::class], version = 1)
abstract class InnobuzzDatabase :RoomDatabase(){

    abstract fun postDao() : PostDao

    companion object{
        @Volatile
        private var INSTANCE : InnobuzzDatabase? = null


        fun getDatabase(context : Context):InnobuzzDatabase{
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        InnobuzzDatabase::class.java,
                        "InnobuzzDatabase")
                        .build()
                }
            }
            return INSTANCE!!
        }

    }
}