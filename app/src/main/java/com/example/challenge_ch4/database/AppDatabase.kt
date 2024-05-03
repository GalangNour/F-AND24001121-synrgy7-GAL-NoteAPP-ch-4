package com.example.challenge_ch4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge_ch4.model.Note
import com.example.challenge_ch4.model.User

@Database(entities = [Note::class, User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract val noteDao: NoteDao
    abstract val userDao : UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "NOTE_DATABASE"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}