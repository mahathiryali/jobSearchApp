package com.example.jobsearchapplication
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
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

        val homeBtn = findViewById<Button>(R.id.home)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val resumesBtn = findViewById<ImageView> (R.id.resumes)
        resumesBtn.setOnClickListener  {
            val intent = Intent(this@Resources, Resumes::class.java)
            startActivity(intent)
//            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show()
        }

        val interviewTipsBtn = findViewById<ImageView>(R.id.preparation)
        interviewTipsBtn.setOnClickListener {
            val intent3 = Intent(this, Preparation::class.java)
            startActivity(intent3)
//            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show()
        }

        val FAQBtn = findViewById<ImageView>(R.id.faq)
        FAQBtn.setOnClickListener {
            val intent4 = Intent(this, FAQ::class.java)
            startActivity(intent4)
        }
    }
}