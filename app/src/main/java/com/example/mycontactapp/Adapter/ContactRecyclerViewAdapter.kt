package com.example.mycontactapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.Data.PersonDetail
import com.example.mycontactapp.R

class ContactRecyclerViewAdapter(private val contactList: ArrayList<PersonDetail>,var listener: SetItemClickListener) :RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var firstName :TextView? = view.findViewById(R.id.firstName)
        var lastName :TextView? = view.findViewById(R.id.lastName)
        var email :TextView? = view.findViewById(R.id.email)
        var phone :TextView? = view.findViewById(R.id.phone)
        var abbreviatedText :TextView? = view.findViewById(R.id.abbreviated)

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){

                listener.showItemDetails(position,firstName)

            }
        }

    }

    interface SetItemClickListener{
        fun showItemDetails(position: Int, myView :View?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contactList[position]

        holder.firstName?.text = currentItem.firstName
        holder.lastName?.text = currentItem.lastName
        holder.email?.text = currentItem.email
        holder.phone?.text = currentItem.phone
        holder.abbreviatedText?.text = String.format(currentItem.firstName!![0].uppercase())
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}