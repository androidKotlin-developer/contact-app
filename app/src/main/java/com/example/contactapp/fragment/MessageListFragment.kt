package com.example.contactapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.viewmodel.UserViewModel
import com.example.contactapp.adapter.ListAdapter
import com.example.contactapp.databinding.FragmentMessageListBinding

class MessageListFragment : Fragment() {

    private lateinit var binding: FragmentMessageListBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageListBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root

        val adapter1 = ListAdapter()
        binding.lisRecyclerview.adapter = adapter1
        binding.lisRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter1.setData(user)
        }
        return root
    }
}
