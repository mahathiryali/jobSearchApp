package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class JobSearch : AppCompatActivity() {
    private val defaultRadius = 25
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter
    private lateinit var careerFieldEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var seekBarProgress: TextView
    private lateinit var jobText: TextView
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_search)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        careerFieldEditText = findViewById(R.id.careerField)
        locationEditText = findViewById(R.id.location)
        seekBar = findViewById(R.id.seekBar)
        seekBarProgress = findViewById(R.id.seekBarProgress)
        jobText = findViewById(R.id.radiusText)
        updateJobText(seekBar.progress)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                updateJobText(progress)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        jobRecyclerView = findViewById(R.id.jobRecyclerView)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobAdapter = JobAdapter(emptyList())
        jobRecyclerView.adapter = jobAdapter

        val dividerItemDecoration = DividerItemDecoration(
            jobRecyclerView.context,
            (jobRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        jobRecyclerView.addItemDecoration(dividerItemDecoration)

        jobAdapter.onSaveJobClickListener = { job ->
            saveJobs(job)
        }

        val enterButton = findViewById<Button>(R.id.enter)
        enterButton.setOnClickListener {
            val keyword = careerFieldEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()
            val radius = seekBar.progress
            if (keyword.isNotEmpty() && location.isNotEmpty()) {
                updateJobText(radius)
                fetchJobs(keyword, location, radius)
            } else {
                showToast("Please enter both a career field and location.")
            }
        }

        val clearBtn = findViewById<Button>(R.id.clear)
        clearBtn.setOnClickListener {
            clearFields()
        }
    }

    private fun clearFields() {
        careerFieldEditText.text.clear()
        locationEditText.text.clear()
        seekBar.progress = defaultRadius
        updateJobText(defaultRadius)
        jobAdapter.updateJobs(emptyList())
    }

    private fun updateJobText(progress: Int) {
        val career = careerFieldEditText.text.toString().trim()
        val location = locationEditText.text.toString().trim()
        seekBarProgress.text = "Current Radius: $progress miles"
        jobText.text = "Finding $career jobs in a $progress mile radius of $location"
    }

    private fun fetchJobs(keyword: String, location: String, radius: Int) {
        val apiUrl = BuildConfig.API_URL
        val apiId = BuildConfig.API_ID
        val apiKey = BuildConfig.API_KEY
        if (apiUrl.isNullOrEmpty() || apiId.isNullOrEmpty() || apiKey.isNullOrEmpty()) {
            showToast("Please enter an API URL, ID, and KEY.")
            return
        }
        val url = "$apiUrl?app_id=$apiId&app_key=$apiKey&what=$keyword&where=$location&distance=$radius"

        thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode = connection.responseCode
                val inputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
                    connection.inputStream.bufferedReader().use { it.readText() }
                } else {
                    connection.errorStream.bufferedReader().use { it.readText() }
                }

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val jobResult = Gson().fromJson(inputStream, JobResult::class.java)
                    runOnUiThread {
                        jobAdapter.updateJobs(jobResult.results)
                    }
                } else {
                    val errorResponse = inputStream
                    runOnUiThread {
                        showToast("Failed to fetch jobs: $errorResponse")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    showToast("Exception occurred: ${e.message}")
                }
            }
        }
    }

    private fun saveJobs(job: Job) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            val userJobsRef = db.collection("users").document(user.uid).collection("savedJobs")
            userJobsRef.add(job)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Job saved successfully.")
                    } else {
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        showToast("Failed to save job: $errorMessage")
                        Log.e("FirestoreError", "Failed to save job: $errorMessage", task.exception)
                    }
                }
        } else {
            showToast("User not logged in.")
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}