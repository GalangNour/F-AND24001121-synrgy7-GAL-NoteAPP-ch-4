package com.example.challenge_ch4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_ch4.database.UserDao
import com.example.challenge_ch4.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao : UserDao) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<User?>()
    val authenticatedUser: LiveData<User?> = _authenticatedUser


    fun registerUser(user : User){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userDao.register(user)
            }
        }
    }

    fun loginUser(userName : String, userPass : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = userDao.login(userName, userPass)
                if (user != null) {
                    _authenticatedUser.postValue(user)
                }
            }
        }
    }

    fun destroyAuthenticatedUser() {
        _authenticatedUser.postValue(null)
    }

}