package com.example.jobsearchapplication
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop

class FAQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val q1Btn = findViewById<Button>(R.id.question1)
        val q1AnswerBtn = findViewById<TextView>(R.id.question1Answer)
        q1AnswerBtn.visibility = View.INVISIBLE
        q1Btn.setOnClickListener {
            q1AnswerBtn.showHide()
        }

        val q2Btn = findViewById<Button>(R.id.question2)
        val q2AnswerBtn = findViewById<TextView>(R.id.question2Answer)
        q2AnswerBtn.visibility = View.INVISIBLE
        q2Btn.setOnClickListener {
            q2AnswerBtn.showHide()
        }

        val q3Btn = findViewById<Button>(R.id.question3)
        val q3AnswerBtn = findViewById<TextView>(R.id.question3Answer)
        q3AnswerBtn.visibility = View.INVISIBLE
        q3Btn.setOnClickListener {
            q3AnswerBtn.showHide()
        }

        val q4Btn = findViewById<Button>(R.id.question4)
        val q4AnswerBtn = findViewById<TextView>(R.id.question4Answer)
        q4AnswerBtn.visibility = View.INVISIBLE
        q4Btn.setOnClickListener {
            q4AnswerBtn.showHide()
        }

        val q5Btn = findViewById<Button>(R.id.question5)
        val q5AnswerBtn = findViewById<TextView>(R.id.question5Answer)
        q5AnswerBtn.visibility = View.INVISIBLE
        q5Btn.setOnClickListener {
            q5AnswerBtn.showHide()
        }
    }
}

private fun TextView.showHide() {
    visibility = if (visibility == View.VISIBLE){
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}