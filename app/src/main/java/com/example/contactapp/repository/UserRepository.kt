package com.example.contactapp.repository

import androidx.lifecycle.LiveData
import com.example.contactapp.database.ContactDao
import com.example.contactapp.model.User

class UserRepository(private val userDao: ContactDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

     fun addUser(user: User){
        userDao.addUser(user)
    }

}
