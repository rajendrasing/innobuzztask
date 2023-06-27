package com.rr.innobuzztask.application

import android.app.Application
import android.content.Context
import com.rr.innobuzztask.database.roomdatabase.InnobuzzDatabase


class MyApp : Application() {

    companion object{
        lateinit var appContext : Context
        private set

        var myRoomDb : InnobuzzDatabase? = null

        fun getApplicationContext() : Context{
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this
        myRoomDb = InnobuzzDatabase.getDatabase(this)

    }
}

