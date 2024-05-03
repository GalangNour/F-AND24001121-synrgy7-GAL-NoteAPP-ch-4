package com.example.challenge_ch4.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.challenge_ch4.model.User

@Dao
interface UserDao {

    @Insert
    fun register(user: User)

    @Query("SELECT * FROM user_table WHERE user_name LIKE :userName AND user_pass LIKE :userPass")
    fun login(userName: String, userPass: String): User?

}