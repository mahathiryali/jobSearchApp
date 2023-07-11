package com.example.jobsearchapplication
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Resources : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resource)

        val homeBtn = findViewById(R.id.home) as Button
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val resumesBtn = findViewById<ImageView> (R.id.resumes)
        resumesBtn.setOnClickListener {
            val intent2 = Intent(resumesBtn.context, Resumes::class.java)
            resumesBtn.context.startActivity(intent2)
        }

        val interviewTipsBtn = findViewById(R.id.preparation) as ImageView
        interviewTipsBtn.setOnClickListener {
            val intent3 = Intent(this, Preparation::class.java)
            startActivity(intent3)
        }

        val FAQBtn = findViewById(R.id.faq) as ImageView
        FAQBtn.setOnClickListener {
            val intent4 = Intent(this, FAQ::class.java)
            startActivity(intent4)
        }
    }
}