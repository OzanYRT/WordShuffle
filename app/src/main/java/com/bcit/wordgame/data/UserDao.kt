package com.bcit.wordgame.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    fun getAll(): List<LocalUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LocalUser)

    @Delete
    fun delete(user: LocalUser)

    @Query("UPDATE users_table SET high_score = :newHighScore WHERE first_name = :userName")
    fun updateHighScore(userName: String, newHighScore: Int)
}