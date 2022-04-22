package com.example.roomsqlite.Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>


    @Query("SELECT NOT EXISTS(SELECT * FROM users WHERE name = :name)")
    fun isNotExists(name: String?): Boolean

    @Insert
    fun insertAll(vararg user:User)
}