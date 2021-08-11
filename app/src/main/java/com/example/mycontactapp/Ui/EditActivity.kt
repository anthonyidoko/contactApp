package com.example.mycontactapp.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mycontactapp.Data.PersonDetail
import com.example.mycontactapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val btnUpdate: Button = findViewById(R.id.btnUpdate)
        val firstName: EditText = findViewById(R.id.firstName)
        val lastName: EditText = findViewById(R.id.lastName)
        val phoneNumber: EditText = findViewById(R.id.phoneNumber)
        val email: EditText = findViewById(R.id.email)
        val mobile: EditText = findViewById(R.id.mobileNumber)

        val node = intent.getStringExtra("node").toString()
        val node2 = intent.getStringExtra("node").toString()
        val node3 = intent.getStringExtra("node").toString()
        val node4 = intent.getStringExtra("node").toString()

        firstName.setText(node)
        lastName.setText(node2)
        phoneNumber.setText(node3)
        email.setText(node4)
        mobile.setText(node)

        btnUpdate.setOnClickListener {
            val updatedFirstName = firstName.text.toString()
            val updatedLastName = lastName.text.toString()
            val updatedPhoneNumber = phoneNumber.text.toString()
            val updatedEmail = email.text.toString()
            val updatedMobile = mobile.text.toString()

            //Initialize database
            database = FirebaseDatabase.getInstance().getReference("PersonDetail")

            //Save to database
            val personDetail = PersonDetail(
                updatedFirstName,updatedLastName,updatedPhoneNumber,updatedMobile,updatedEmail
            )

            //Clear the fields
            database.child(node).setValue(personDetail).addOnSuccessListener {
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this,FirebaseDataActivity::class.java)
            startActivity(intent)
        }


    }
}