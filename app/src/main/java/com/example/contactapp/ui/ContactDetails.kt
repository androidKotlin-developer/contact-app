package com.example.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactapp.databinding.ActivityContactDetailsBinding
import com.example.contactapp.util.Constant

class ContactDetails : AppCompatActivity() {
    private lateinit var binding : ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnMessageButton.setOnClickListener {
            val intent = Intent(this,SendMessage::class.java)
            intent.putExtra(Constant.PERSON_NAME,binding.tvContactName.text)
            intent.putExtra(Constant.PERSON_NUMBER,binding.tvContactPhone.text)
            startActivity(intent)
        }

        binding.tvContactName.text = intent.getStringExtra(Constant.PERSON_NAME)
        binding.tvContactPhone.text = intent.getStringExtra(Constant.PERSON_NUMBER)

    }
}
