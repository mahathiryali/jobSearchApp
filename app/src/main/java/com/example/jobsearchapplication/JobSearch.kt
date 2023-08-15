package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_search)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val seek = findViewById<SeekBar>(R.id.seekBar)
        val seekBarProgress = findViewById<TextView>(R.id.seekBarProgress)
        val jobText = findViewById<TextView>(R.id.radiusText)
        seekBarProgress.text = "Default Radius: ${seek.progress}" + " miles"
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                seekBarProgress.text = "Current Radius: $progress" + " miles"
                jobText.text = "Finding software engineering jobs in a $progress" + " mile radius of Atlanta"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}