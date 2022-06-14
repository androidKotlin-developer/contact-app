package com.example.contactapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactapp.model.User
import com.example.contactapp.util.Constant

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract  class ContactDataBase  : RoomDatabase(){

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDataBase? = null

        fun getDatabase(context: Context): ContactDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDataBase::class.java,
                Constant.DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
