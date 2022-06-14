package com.example.contactapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_table")
data class ContactX(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    @PrimaryKey( autoGenerate = true)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
) : Serializable
