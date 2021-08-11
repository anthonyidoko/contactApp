package com.example.mycontactapp.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mycontactapp.Data.PersonDetail
import com.example.mycontactapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val firstName = findViewById<TextView>(R.id.firstName)
        val lastName = findViewById<TextView>(R.id.lastName)
        val phoneNumber = findViewById<TextView>(R.id.phoneNumber)
        val email = findViewById<TextView>(R.id.email)
        val editIcon = findViewById<ImageView>(R.id.editIcon)
        val deleteItem = findViewById<TextView>(R.id.deleteItem)
        val threeDotIcon = findViewById<ImageView>(R.id.threeDotIcon)


        val data = intent.getParcelableExtra<PersonDetail>("EXTRA_DATA")

        if (data != null) {
            firstName.text = data.firstName
            lastName.text = data.lastName
            phoneNumber.text = data.phone
            email.text = data.email
        }

        editIcon.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("node", firstName.text)
            intent.putExtra("node2", lastName.text)
            intent.putExtra("node3", phoneNumber.text)
            intent.putExtra("node4", email.text)
            startActivity(intent)
        }

        threeDotIcon.setOnClickListener {
            deleteItem.visibility = View.VISIBLE
        }

        deleteItem.setOnClickListener {
            val firstName = firstName.text.toString()
            database = FirebaseDatabase.getInstance().getReference("PersonDetail")
            val personDetail = null

            //Clear the fields
            database.child(firstName).setValue(personDetail).addOnSuccessListener {
                Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,FirebaseDataActivity::class.java)
                startActivity(intent)

            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}