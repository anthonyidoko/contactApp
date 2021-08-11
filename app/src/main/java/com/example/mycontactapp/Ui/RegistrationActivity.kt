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
import com.example.mycontactapp.databinding.ActivityRegistrationBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding :ActivityRegistrationBinding
    private lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Reference the various views
        val saveText: TextView = findViewById(R.id.saveText)
        val backArrow: ImageView = findViewById(R.id.backArrow)
        val editIcon: ImageView = findViewById(R.id.editIcon)
        val threeDotIcon: ImageView = findViewById(R.id.threeDotIcon)
        val deleteItem: TextView = findViewById(R.id.deleteItem)

        //Save to database and Navigate to the contact page
        saveText.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val phoneNumber = binding.phone.text.toString()
            val mobileNumber = binding.mobile.text.toString()
            val email = binding.email.text.toString()

            //Initialize database
            database = FirebaseDatabase.getInstance().getReference("PersonDetail")

            //Save to database
            val personDetail = PersonDetail(firstName,lastName,phoneNumber,mobileNumber,email)

            //Clear the fields
            database.child(firstName).setValue(personDetail).addOnSuccessListener {
                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.phone.text.clear()
                binding.mobile.text.clear()
                binding.email.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this,FirebaseDataActivity::class.java)
            startActivity(intent)
        }

        //SetOnclick listener with intent for the backArrow object, to go back to main activity
        backArrow.setOnClickListener {
            val intent = Intent(this, FirebaseDataActivity::class.java)
            startActivity(intent)
        }

    }

}