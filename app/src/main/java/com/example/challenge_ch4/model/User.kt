package com.example.challenge_ch4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long? = null,

    @ColumnInfo(name = "user_name")
    val userName : String,

    @ColumnInfo(name = "user_email")
    val userEmail : String,

    @ColumnInfo(name = "user_pass")
    val userPassword : String

)
