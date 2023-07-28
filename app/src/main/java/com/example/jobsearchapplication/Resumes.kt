package com.example.jobsearchapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Resumes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resumes)

        val homeBtn = findViewById<Button>(R.id.home)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val resourcesBtn = findViewById<Button>(R.id.back)
        resourcesBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }

        val template1Btn = findViewById<ImageView>(R.id.template1)
        template1Btn.setOnClickListener {
            val intent = Intent(this, Template1::class.java)
            startActivity(intent)
        }

        val template2Btn = findViewById<ImageView>(R.id.template2)
        template2Btn.setOnClickListener {
            val intent = Intent(this, Template2::class.java)
            startActivity(intent)
        }
    }
}