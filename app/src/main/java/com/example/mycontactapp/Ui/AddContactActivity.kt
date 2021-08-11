package com.example.mycontactapp.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mycontactapp.R

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        val arrowBack : ImageView = findViewById(R.id.arrowBack)
        val editIcon :ImageView = findViewById(R.id.editIcon)


        arrowBack.setOnClickListener {
            val intent = Intent(this, FirebaseDataActivity::class.java)
            startActivity(intent)
        }
    }
}