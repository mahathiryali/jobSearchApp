package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var savedJobsRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter
    private lateinit var jobList: MutableList<Job>

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
        setupProfileLabels(R.id.profileInfoLabel, R.id.profileInfoContent)
        setupProfileLabels(R.id.savedJobsLabel, R.id.savedJobsRecyclerView)
        createProfileContent()
        setupRecyclerView()
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
        } ?: run {
            profileTitle.text = "User not logged in"
        }
    }

    private fun setupProfileLabels(label: Int, content: Int) {
        val itemLabel = findViewById<LinearLayout>(label)
        val itemContent = findViewById<View>(content)
        itemContent.visibility = View.GONE
        itemLabel.setOnClickListener {
            itemContent.visibility = if (itemContent.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun createProfileContent() {
        val userId = auth.currentUser?.uid
        val profileInfoContent = findViewById<TextView>(R.id.profileInfoContent)
        userId?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val username = document.getString("username")
                        val email = document.getString("email")
                        val firstName = document.getString("firstName")
                        val lastName = document.getString("lastName")
                        profileInfoContent.text =
                            buildString {
                                append("Username: $username \n")
                                append("Email: $email \n")
                                append("First Name: $firstName \n")
                                append("Last Name: $lastName \n")
                            }
                    } else {
                        profileInfoContent.text = "User data not found"
                    }
                }
                .addOnFailureListener { e ->
                    profileInfoContent.text = "Failed to retrieve user data: ${e.message}"
                }
        } ?: run {
            profileInfoContent.text = "User not logged in"
        }
    }

    private fun setupRecyclerView() {
        savedJobsRecyclerView = findViewById(R.id.savedJobsRecyclerView)
        savedJobsRecyclerView.layoutManager = LinearLayoutManager(this)
        jobList = mutableListOf()
        jobAdapter = JobAdapter(jobList, showSaveButton = false)
        savedJobsRecyclerView.adapter = jobAdapter
        val dividerItemDecoration = DividerItemDecoration(
            savedJobsRecyclerView.context,
            (savedJobsRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        savedJobsRecyclerView.addItemDecoration(dividerItemDecoration)
        fetchSavedJobs()
    }

    private fun fetchSavedJobs() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            val userJobsRef = db.collection("users").document(user.uid).collection("savedJobs")
            userJobsRef.get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val job = document.toObject(Job::class.java)
                        jobList.add(job)
                    }
                    jobAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.e("FirestoreError", "Error fetching saved jobs: ${exception.message}", exception)
                    showToast("Failed to load saved jobs.")
                }
        } else {
            showToast("User not logged in.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}