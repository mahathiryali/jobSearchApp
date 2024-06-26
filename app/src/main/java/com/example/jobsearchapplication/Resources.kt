package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Resources : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resource)

        val homeBtn = findViewById<Button>(R.id.home)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val resumesBtn = findViewById<ImageView>(R.id.resumes)
        resumesBtn.setOnClickListener  {
            val intent = Intent(this@Resources, Resumes::class.java)
            startActivity(intent)
        }

        val interviewTipsBtn = findViewById<ImageView>(R.id.preparation)
        interviewTipsBtn.setOnClickListener {
            val intent = Intent(this, Preparation::class.java)
            startActivity(intent)
        }

        val faqBtn = findViewById<ImageView>(R.id.faq)
        faqBtn.setOnClickListener {
            val intent = Intent(this, FAQ::class.java)
            startActivity(intent)
        }
    }
}