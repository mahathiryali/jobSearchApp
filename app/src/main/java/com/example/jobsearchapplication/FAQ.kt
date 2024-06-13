package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout

class FAQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }

        setupQuestion(R.id.question1, R.id.question1Answer)
        setupQuestion(R.id.question2, R.id.question2Answer)
        setupQuestion(R.id.question3, R.id.question3Answer)
        setupQuestion(R.id.question4, R.id.question4Answer)
        setupQuestion(R.id.question5, R.id.question5Answer)
    }

    private fun setupQuestion(questionId: Int, answerId: Int) {
        val questionLayout = findViewById<LinearLayout>(questionId)
        val answerText = findViewById<TextView>(answerId)
        answerText.visibility = View.GONE

        questionLayout.setOnClickListener {
            if (answerText.visibility == View.GONE) {
                answerText.visibility = View.VISIBLE
            } else {
                answerText.visibility = View.GONE
            }
        }
    }
}