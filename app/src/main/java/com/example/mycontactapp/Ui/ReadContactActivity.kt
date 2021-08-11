package com.example.mycontactapp.Ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.Adapter.ReadContactAdapter
import com.example.mycontactapp.Data.ReadPersonDataClass
import com.example.mycontactapp.R


class ReadContactActivity : AppCompatActivity() {

    val To_READ_CONTACT = 0
    lateinit var contactRecyclerView : RecyclerView
    lateinit var contactList : MutableList<ReadPersonDataClass>
    lateinit var permissionText:TextView
    lateinit var btnReadContact : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_contact)

        contactRecyclerView = findViewById(R.id.myRecyclerView)

        btnReadContact= findViewById(R.id.btnReadContact)
        permissionText = findViewById(R.id.permissionText)

        contactList = ArrayList()
        btnReadContact.setOnClickListener {
           getContacts()

        }
    }

    fun hasPermissionToReadContact() = ActivityCompat.checkSelfPermission(this,
        Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED


    fun getContacts(){
        if (!hasPermissionToReadContact()){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_CONTACTS),To_READ_CONTACT)
        } else{

            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null,null)
            while (contacts!!.moveToNext()){
                val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                val contactObject = ReadPersonDataClass(name,number, name[0])
                contactList.add(contactObject)

        }
        contactRecyclerView.adapter = ReadContactAdapter(contactList)
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        contactRecyclerView.setHasFixedSize(true)

    }}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions.isNotEmpty() && requestCode == 0)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                btnReadContact.visibility = View.GONE
                permissionText.visibility = View.GONE

                getContacts()
            } else{
                permissionText.visibility = View.VISIBLE
            }
    }
}