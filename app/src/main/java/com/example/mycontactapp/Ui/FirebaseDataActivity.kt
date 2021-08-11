package com.example.mycontactapp.Ui

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.Adapter.ContactRecyclerViewAdapter
import com.example.mycontactapp.Data.PersonDetail
import com.example.mycontactapp.R
import com.google.firebase.database.*

class FirebaseDataActivity : AppCompatActivity(), ContactRecyclerViewAdapter.SetItemClickListener {

    lateinit var databaseReference : DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var adapterList :ArrayList<PersonDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_data)
        recyclerView = findViewById(R.id.contactDetailRecyclerViewData)


        adapterList = arrayListOf()
        getPersonDetail()


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        val registerNewContact : ImageView = findViewById(R.id.registerNewContact)

        registerNewContact.setOnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }


    }

    fun getPersonDetail(){
        databaseReference = FirebaseDatabase.getInstance().getReference("PersonDetail")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (personSnapShot in snapshot.children){
                        val person = personSnapShot.getValue(PersonDetail::class.java)
                        if (person != null){
                            adapterList.add(person)
                        }
                    }
                }

                recyclerView.adapter = ContactRecyclerViewAdapter(adapterList,this@FirebaseDataActivity)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun showItemDetails(position: Int, myView: View?) {

        val options = ActivityOptions.makeSceneTransitionAnimation(this,
            Pair.create(myView, "firstName"),

        )

        val clickedItem :PersonDetail = adapterList[position]
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra("EXTRA_DATA", clickedItem)
        startActivity(intent, options.toBundle())
    }


}