package com.example.contactapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contact(
    @SerializedName("contacts")
    val contacts: List<ContactX>,

) : Serializable
