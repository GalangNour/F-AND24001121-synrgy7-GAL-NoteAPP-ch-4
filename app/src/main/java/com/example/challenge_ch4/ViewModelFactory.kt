package com.example.challenge_ch4

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_ch4.database.AppDatabase
import com.example.challenge_ch4.database.NoteDao
import com.example.challenge_ch4.database.UserDao
import com.example.challenge_ch4.viewmodel.NoteViewModel
import com.example.challenge_ch4.viewmodel.UserViewModel

class ViewModelFactory private constructor(
    private val noteDao : NoteDao,
    private val userDao: UserDao,
    ) : ViewModelProvider.Factory{

        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context) : ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(
                        AppDatabase.getInstance(context).noteDao,
                        AppDatabase.getInstance(context).userDao
                    )
                }
        }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(NoteViewModel::class.java) -> {
                NoteViewModel(noteDao) as T
            }
            modelClass.isAssignableFrom(UserViewModel::class.java)-> {
                UserViewModel(userDao) as T
            }
            else -> throw Throwable("Unknown Viewmodel class : " + modelClass.name)
        }
    }

    }
