package com.example.contactapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactapp.fragment.ContactsLIstFragment
import com.example.contactapp.fragment.MessageListFragment


class ViewPagerAdapter(fragmentManager : FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                ContactsLIstFragment()
            }
            1->{
                MessageListFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}
