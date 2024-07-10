package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var savedJobsRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter
    private lateinit var jobList: MutableList<Job>
    private lateinit var initialUsername: String
    private lateinit var initialEmail: String
    private lateinit var initialFirstName: String
    private lateinit var initialLastName: String
    private lateinit var initialPassword: String
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        usernameEditText = findViewById(R.id.usernameEdit)
        emailEditText = findViewById(R.id.emailEdit)
        firstNameEditText = findViewById(R.id.firstNameEdit)
        lastNameEditText = findViewById(R.id.lastNameEdit)
        passwordEditText = findViewById(R.id.passwordEdit)

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

        val saveBtn = findViewById<Button>(R.id.saveButton)
        loadUserData()
        saveBtn.setOnClickListener {
            verifyEmail()
            updateUserProfile()
        }

        createTitle()
        setupProfileLabels(R.id.viewOrEditProfileInfoLabel, R.id.viewOrEditProfileInfoContent)
        setupProfileLabels(R.id.savedJobsLabel, R.id.savedJobsRecyclerView)
        setupProfileLabels(R.id.signOutLabel, R.id.signOutContent)
        setupRecyclerView()
        signUserOut()
        handleDoNotSignOut(R.id.signOutContent, R.id.doNotSignOutBtn)
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

    private fun setupRecyclerView() {
        savedJobsRecyclerView = findViewById(R.id.savedJobsRecyclerView)
        savedJobsRecyclerView.layoutManager = LinearLayoutManager(this)
        jobList = mutableListOf()
        jobAdapter = JobAdapter(jobList, false, true)
        savedJobsRecyclerView.adapter = jobAdapter

        val dividerItemDecoration = DividerItemDecoration(
            savedJobsRecyclerView.context,
            (savedJobsRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        savedJobsRecyclerView.addItemDecoration(dividerItemDecoration)

        jobAdapter.onDeleteJobClickListener = { job ->
            deleteJob(job)
        }
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

    private fun deleteJob(job: Job) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            val userJobsRef = db.collection("users").document(user.uid).collection("savedJobs")
            userJobsRef.whereEqualTo("title", job.title).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                            .addOnSuccessListener {
                                showToast("Job deleted successfully")
                                jobList.remove(job)
                                jobAdapter.notifyDataSetChanged()
                            }
                            .addOnFailureListener { e ->
                                showToast("Failed to delete job: ${e.message}")
                            }
                    }
                }
                .addOnFailureListener { e ->
                    showToast("Failed to find job to delete: ${e.message}")
                }
        } else {
            showToast("User not logged in.")
        }
    }

    private fun signUserOut() {
        val signOutBtn = findViewById<Button>(R.id.signOutBtn)
        signOutBtn.setOnClickListener {
            auth.signOut()
            showToast("Signed out successfully!")
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
            }, 1500)
        }
    }

    private fun handleDoNotSignOut(label: Int, content: Int) {
        val itemLabel = findViewById<LinearLayout>(label)
        val itemContent = findViewById<Button>(content)
        itemContent.setOnClickListener {
            itemLabel.visibility = if (itemLabel.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun loadUserData() {
        val user = auth.currentUser
        val userId = user?.uid
        userId?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        initialUsername = document.getString("username") ?: ""
                        initialEmail = document.getString("email") ?: ""
                        initialFirstName = document.getString("firstName") ?: ""
                        initialLastName = document.getString("lastName") ?: ""
                        initialPassword = document.getString("password") ?: ""

                        usernameEditText.setText(initialUsername)
                        emailEditText.setText(initialEmail)
                        firstNameEditText.setText(initialFirstName)
                        lastNameEditText.setText(initialLastName)
                        passwordEditText.setText(initialPassword)
                        Log.d("UserProfile", "Loaded initial email: $initialEmail, initial password: $initialPassword")
                    } else {
                        showToast("No user data found")
                    }
                }
                .addOnFailureListener { e ->
                    showToast("Failed to retrieve user data: ${e.message}")
                }
        }
    }

    private fun reauthenticateAndChangeEmail(currentPassword: String, newEmail: String) {
        val user = auth.currentUser
        val credential = EmailAuthProvider.getCredential(user?.email!!, currentPassword)

        user.reauthenticate(credential)
            .addOnCompleteListener { reauthTask ->
                if (reauthTask.isSuccessful) {
                    user.updateEmail(newEmail)
                        .addOnCompleteListener { emailUpdateTask ->
                            if (emailUpdateTask.isSuccessful) {
                                showToast("Email updated successfully in Firebase Auth!")
                            } else {
                                showToast("Failed to update email in Firebase Auth: ${emailUpdateTask.exception?.message}")
                                Log.e("UserProfile", "Error updating email: ${emailUpdateTask.exception}")
                            }
                        }
                } else {
                    showToast("Re-authentication failed: ${reauthTask.exception?.message}")
                    Log.e("UserProfile", "Error re-authenticating: ${reauthTask.exception}")
                }
            }
    }

    private fun verifyEmail() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Verification email sent!")
            } else {
                showToast("Failed to send verification email: ${task.exception?.message}")
            }
        }
    }

    private fun updateUserProfile() {
        val user = auth.currentUser
        val userId = user?.uid
        val currentUsername = usernameEditText.text.toString()
        val currentEmail = emailEditText.text.toString()
        val currentFirstName = firstNameEditText.text.toString()
        val currentLastName = lastNameEditText.text.toString()
        val currentPassword = passwordEditText.text.toString()
        val updatedFields = mutableMapOf<String, Any>()

        if (currentUsername != initialUsername) updatedFields["username"] = currentUsername
        if (currentEmail != initialEmail) updatedFields["email"] = currentEmail
        if (currentFirstName != initialFirstName) updatedFields["firstName"] = currentFirstName
        if (currentLastName != initialLastName) updatedFields["lastName"] = currentLastName
        if (currentPassword != initialPassword) updatedFields["password"] = currentPassword

        userId?.let {
            if (updatedFields.isNotEmpty()) {
                firestore.collection("users").document(it).update(updatedFields)
                    .addOnSuccessListener {
                        showToast("Profile updated successfully in Firestore!")
                    }
                    .addOnFailureListener { e ->
                        showToast("Failed to update profile in Firestore: ${e.message}")
                    }
            } else {
                showToast("No changes to update in Firestore")
            }

            if (currentEmail.isNotEmpty() && currentEmail != initialEmail) {
                reauthenticateAndChangeEmail(initialPassword, currentEmail)
            }

            if (currentPassword.isNotEmpty() && currentPassword != initialPassword) {
                user?.updatePassword(currentPassword)
                    ?.addOnCompleteListener { passwordUpdateTask ->
                        if (passwordUpdateTask.isSuccessful) {
                            showToast("Password updated successfully in Firebase Auth!")
                        } else {
                            showToast("Failed to update password in Firebase Auth: ${passwordUpdateTask.exception?.message}")
                            Log.e("UserProfile", "Error updating password: ${passwordUpdateTask.exception}")
                        }
                    }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}