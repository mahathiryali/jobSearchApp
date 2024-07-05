package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        createTitle()
    }

    private fun createTitle() {
        val userId = auth.currentUser?.uid
        val profileTitle = findViewById<TextView>(R.id.profileTitle)
        userId?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val firstName = document.getString("firstName")
                        profileTitle.text = "Welcome, $firstName"
                    } else {
                        profileTitle.text = "User data not found"
                    }
                }
                .addOnFailureListener { e ->
                    profileTitle.text = "Failed to retrieve user data: ${e.message}"
                }
        }
    }
}