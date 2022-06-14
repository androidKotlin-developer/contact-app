package com.example.contactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.model.Contact

class DataAdapter(private val dataList: Contact, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = this.itemView.findViewById(R.id.tv_contact_name)

        fun initialize(item: Contact, action: OnItemClickListener) {
            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contactlist_dsign, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataList.contacts[position].name
        holder.initialize(dataList,listener)

    }

    override fun getItemCount(): Int {
        return dataList.contacts.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: Contact, position: Int)
    }
}
