package com.example.contactapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactapp.model.User

@Dao
interface ContactDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addUser(user: User)

    @Update
     fun updateUser(user: User)

    @Delete
     fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
     fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<User>>
}
