package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactapp.adapter.ViewPagerAdapter
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.util.Constant
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val isFrom = intent!!.getBooleanExtra(Constant.IS_FORM, false)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.viewPager2.adapter = adapter

        if (isFrom) {
            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, pos ->
                binding.tabLayout.setScrollPosition(pos, 0f, true)
                binding.viewPager2.setCurrentItem(pos, true)
                when (pos) {
                    0 -> {
                        tab.text = Constant.CONTACT
                    }
                    1 -> {
                        tab.text = Constant.MESSAGE
                    }
                }
            }.attach()
        } else {
            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = Constant.CONTACT
                    }
                    1 -> {
                        tab.text = Constant.MESSAGE
                    }
                }
            }.attach()
        }


    }
}
