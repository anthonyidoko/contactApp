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

        firstName.setText(node)

        btnUpdate.setOnClickListener {
            val updatedFirstName = firstName.text.toString()
            val lastName = lastName.text.toString()
            val phoneNumber = phoneNumber.text.toString()
            val email = email.text.toString()
            val mobile = mobile.text.toString()

            //Initialize database
            database = FirebaseDatabase.getInstance().getReference("PersonDetail")

            //Save to database
            val personDetail = PersonDetail(updatedFirstName,lastName,phoneNumber,mobile,email)

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