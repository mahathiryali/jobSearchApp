package com.example.jobsearchapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val createAccountText = findViewById<TextView>(R.id.createAccountText)
        createAccountText.text = Html.fromHtml("<u>Create Account</u>")
        createAccountText.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }
    }
}