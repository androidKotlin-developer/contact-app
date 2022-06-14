package com.example.contactapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.ui.ContactDetails
import com.example.contactapp.adapter.DataAdapter
import com.example.contactapp.databinding.FragmentContactsLIstBinding
import com.example.contactapp.model.Contact
import com.example.contactapp.util.Constant
import com.example.contactapp.util.Util

class ContactsLIstFragment : Fragment(), DataAdapter.OnItemClickListener {
    private lateinit var binding: FragmentContactsLIstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsLIstBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val contact = Util.getFileData(context, Constant.CONTACT_FILE_NAME)

        binding.contactsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val dataAdapter = DataAdapter(contact!!, this)
        binding.contactsList.adapter = dataAdapter

        return root
    }

    override fun onItemClick(item: Contact, position: Int) {
        val intent = Intent(context, ContactDetails::class.java)
        intent.putExtra(Constant.PERSON_NAME,item.contacts[position].name)
        intent.putExtra(Constant.PERSON_NUMBER,item.contacts[position].phone)
        startActivity(intent)


    }


}
