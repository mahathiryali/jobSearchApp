package com.example.jobsearchapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val resourcesBtn = findViewById<Button>(R.id.resources)
        resourcesBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }

        val jobSearchBtn = findViewById<Button>(R.id.jobSearch)
        jobSearchBtn.setOnClickListener {
            val intent = Intent(this, JobSearch::class.java)
            startActivity(intent)
        }
    }
}