package com.example.mycontactapp.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mycontactapp.Data.PersonDetail
import com.example.mycontactapp.R

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val firstName = findViewById<TextView>(R.id.firstName)
        val lastName = findViewById<TextView>(R.id.lastName)
        val phoneNumber = findViewById<TextView>(R.id.phoneNumber)
        val email = findViewById<TextView>(R.id.email)

        val data = intent.getParcelableExtra<PersonDetail>("EXTRA_DATA")

        if (data != null) {
            firstName.text = data.firstName
            lastName.text = data.lastName
            phoneNumber.text = data.phone
            email.text = data.email
        }
    }
}