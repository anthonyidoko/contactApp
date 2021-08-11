package com.example.mycontactapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.Data.ReadPersonDataClass
import com.example.mycontactapp.R

class ReadContactAdapter(val contactList :MutableList<ReadPersonDataClass>) : RecyclerView.Adapter<ReadContactAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var name: TextView = view.findViewById(R.id.name)
        var phoneNumber: TextView = view.findViewById(R.id.phone)
        var firstLetter: TextView = view.findViewById(R.id.firstLetter)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.read_contact_recycler_view_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contactList[position]

        holder.name.text = currentItem.name
        holder.phoneNumber.text = currentItem.phone
        holder.firstLetter.text = String.format(currentItem.name[0].toString())
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}