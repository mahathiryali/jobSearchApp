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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val resumesBtn = findViewById<ImageView> (R.id.resumes)
        resumesBtn.setOnClickListener  {
            val intent = Intent(this@Resources, Resumes::class.java)
            startActivity(intent)
        }

        val interviewTipsBtn = findViewById<ImageView>(R.id.preparation)
        interviewTipsBtn.setOnClickListener {
            val intent3 = Intent(this, Preparation::class.java)
            startActivity(intent3)
        }

        val FAQBtn = findViewById<ImageView>(R.id.faq)
        FAQBtn.setOnClickListener {
            val intent4 = Intent(this, FAQ::class.java)
            startActivity(intent4)
        }
    }
}