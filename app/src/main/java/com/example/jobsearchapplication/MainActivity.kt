package com.example.jobsearchapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resourcesBtn = findViewById(R.id.resources) as Button
        resourcesBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }
    }
}