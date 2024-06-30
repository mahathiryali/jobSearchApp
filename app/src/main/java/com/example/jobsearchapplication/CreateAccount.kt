package com.example.jobsearchapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateAccount : AppCompatActivity() {
    private lateinit var usernameEdit: EditText
    private lateinit var emailEdit: EditText
    private lateinit var firstNameEdit: EditText
    private lateinit var lastNameEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var createAccountButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)

        usernameEdit = findViewById(R.id.usernameEdit)
        emailEdit = findViewById(R.id.emailEdit)
        firstNameEdit = findViewById(R.id.firstNameEdit)
        lastNameEdit = findViewById(R.id.lastNameEdit)
        passwordEdit = findViewById(R.id.passwordEdit)
        createAccountButton = findViewById(R.id.createAccountButton)

        createAccountButton.setOnClickListener {
            validateInputs()
        }

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs() {
        val username = usernameEdit.text.toString().trim()
        val email = emailEdit.text.toString().trim()
        val firstName = firstNameEdit.text.toString().trim()
        val lastName = lastNameEdit.text.toString().trim()
        val password = passwordEdit.text.toString().trim()

        when {
            TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(password) -> {
                showToast("Inputting text in all fields is required")
            }
            username.length < 8 -> {
                showToast("Username must be at least 8 characters long")
            }
            !email.contains("@") || !email.contains(".") -> {
                showToast("Invalid email address")
            }
            password.length < 8 -> {
                showToast("Password must be at least 8 characters long")
            }
            else -> {
                showToast("Account created successfully")
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                }, 1500)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}