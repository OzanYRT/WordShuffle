package com.bcit.wordgame

import android.app.Application
import androidx.room.Room
import com.bcit.wordgame.data.AppDatabase
import com.bcit.wordgame.data.UsersRepository

class MyApp : Application() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-cool-database")
            .allowMainThreadQueries()
            .build()
    }

    val usersRepository by lazy { UsersRepository(db.userDao()) }

}