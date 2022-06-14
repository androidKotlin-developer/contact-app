package com.example.contactapp.ui

import android.content.Intent
import android.icu.text.DateFormat
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contactapp.viewmodel.UserViewModel
import com.example.contactapp.databinding.ActivitySendMessageBinding
import com.example.contactapp.model.User
import com.example.contactapp.util.Constant
import com.example.contactapp.util.Util
import java.util.*

class SendMessage : AppCompatActivity() {

    lateinit var binding: ActivitySendMessageBinding
    private lateinit var mUserViewModel: UserViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.name.text = intent.getStringExtra(Constant.PERSON_NAME)

        val text ="${Constant.TEXT_TEMPLATE}${getRandomNumberString()}"
        binding.textmessage.setText(text)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.sendbutton.setOnClickListener {

            val currentTime: Date = Calendar.getInstance().time
            val currentTime1= DateFormat.getTimeInstance().format(currentTime)
            val formattedTime = Util.dateFormatter("HH:mm:ss a", "HH:mm a", currentTime1)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constant.IS_FORM,true)
            if (formattedTime != null) {
                Util.sendMessage(binding.textmessage.text.toString())
                insertDataToDatabase(formattedTime)
            }
            startActivity(intent)
            finish()
        }

    }

    private fun insertDataToDatabase(time : String) {

        val name = binding.name.text.toString()
        val message = binding.textmessage.text.toString()

        if (inputCheck(name, message, time)) {
            val user = User(
                0,
                name,
                message,
                time
            )

            mUserViewModel.addUser(user)

        }
    }

    private fun getRandomNumberString(): String {
        val rnd = Random()
        val number: Int = rnd.nextInt(999999)
        return String.format("%06d", number)
    }

    private fun inputCheck(name: String, message: String, currentTime: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(message) && TextUtils.isEmpty(
            currentTime
        ))
    }

}
