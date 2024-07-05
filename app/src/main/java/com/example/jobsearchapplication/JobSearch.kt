package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class JobSearch : AppCompatActivity() {
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobAdapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_search)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val careerFieldEditText = findViewById<EditText>(R.id.careerField)
        val locationEditText = findViewById<EditText>(R.id.location)
        val seek = findViewById<SeekBar>(R.id.seekBar)
        val seekBarProgress = findViewById<TextView>(R.id.seekBarProgress)
        val jobText = findViewById<TextView>(R.id.radiusText)

        seekBarProgress.text = "Default Radius: ${seek.progress} miles"
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                seekBarProgress.text = "Current Radius: $progress miles"
                jobText.text = "Finding jobs in a $progress mile radius"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        jobRecyclerView = findViewById(R.id.jobRecyclerView)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobAdapter = JobAdapter(emptyList())
        jobRecyclerView.adapter = jobAdapter

        val enterButton = findViewById<Button>(R.id.enter)
        enterButton.setOnClickListener {
            val keyword = careerFieldEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()
            val radius = seek.progress

            if (keyword.isNotEmpty() && location.isNotEmpty()) {
                fetchJobs(keyword, location, radius)
            } else {
                Toast.makeText(this, "Please enter both career field and location", Toast.LENGTH_SHORT).show()
            }
        }
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

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}