package com.example.mycontactapp.Ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mycontactapp.R


class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebaseDataButton = findViewById<Button>(R.id.firebaseDataButton)
        val readContactDataButton = findViewById<Button>(R.id.readContactDataButton)

        firebaseDataButton.setOnClickListener {
            val intent = Intent(this, FirebaseDataActivity::class.java)
            startActivity(intent)
        }

        readContactDataButton.setOnClickListener {
            val intent = Intent(this, ReadContactActivity::class.java)
            startActivity(intent)
        }


    }


}