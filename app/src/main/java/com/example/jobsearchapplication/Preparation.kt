package com.example.jobsearchapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class Preparation : AppCompatActivity() {
    lateinit var frontAnim:AnimatorSet
    lateinit var backAnim: AnimatorSet
    var prep1Front = true
    var prep2Front = true
    var prep3Front = true
    var prep4Front = true
    var prep5Front = true
    var prep6Front = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preparation)

        val homeBtn = findViewById<Button>(R.id.homePage)
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }

        val scale = applicationContext.resources.displayMetrics.density
        val prep1Q = findViewById<TextView>(R.id.prep1Question)
        val prep1A =findViewById<TextView>(R.id.prep1Answer)
        val prep1Btn = findViewById<Button>(R.id.prep1Btn)
        prep1Q.cameraDistance = 8000 * scale
        prep1A.cameraDistance = 8000 * scale
        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        prep1Btn.setOnClickListener {
            if (prep1Front) {
                prep1Btn.setText("Show Question")
                frontAnim.setTarget(prep1Q)
                backAnim.setTarget(prep1A)
                frontAnim.start()
                backAnim.start()
                prep1Front = false
            } else {
                prep1Btn.setText("Show Answer")
                frontAnim.setTarget(prep1A)
                backAnim.setTarget(prep1Q)
                backAnim.start()
                frontAnim.start()
                prep1Front = true
            }
        }

        val prep2Q = findViewById<TextView>(R.id.prep2Question)
        val prep2A =findViewById<TextView>(R.id.prep2Answer)
        val prep2Btn = findViewById<Button>(R.id.prep2Btn)

        prep2Btn.setOnClickListener {
            if (prep2Front) {
                prep2Btn.setText("Show Question")
                frontAnim.setTarget(prep2Q)
                backAnim.setTarget(prep2A)
                frontAnim.start()
                backAnim.start()
                prep2Front = false
            } else {
                prep2Btn.setText("Show Answer")
                frontAnim.setTarget(prep2A)
                backAnim.setTarget(prep2Q)
                backAnim.start()
                frontAnim.start()
                prep2Front = true
            }
        }

        val prep3Q = findViewById<TextView>(R.id.prep3Question)
        val prep3A =findViewById<TextView>(R.id.prep3Answer)
        val prep3Btn = findViewById<Button>(R.id.prep3Btn)

        prep3Btn.setOnClickListener {
            if (prep3Front) {
                prep3Btn.setText("Show Question")
                frontAnim.setTarget(prep3Q)
                backAnim.setTarget(prep3A)
                frontAnim.start()
                backAnim.start()
                prep3Front = false
            } else {
                prep3Btn.setText("Show Answer")
                frontAnim.setTarget(prep3A)
                backAnim.setTarget(prep3Q)
                backAnim.start()
                frontAnim.start()
                prep3Front = true
            }
        }

        val prep4Q = findViewById<TextView>(R.id.prep4Question)
        val prep4A =findViewById<TextView>(R.id.prep4Answer)
        val prep4Btn = findViewById<Button>(R.id.prep4Btn)

        prep4Btn.setOnClickListener {
            if (prep4Front) {
                prep4Btn.setText("Show Question")
                frontAnim.setTarget(prep4Q)
                backAnim.setTarget(prep4A)
                frontAnim.start()
                backAnim.start()
                prep4Front = false
            } else {
                prep4Btn.setText("Show Answer")
                frontAnim.setTarget(prep4A)
                backAnim.setTarget(prep4Q)
                backAnim.start()
                frontAnim.start()
                prep4Front = true
            }
        }

        val prep5Q = findViewById<TextView>(R.id.prep5Question)
        val prep5A =findViewById<TextView>(R.id.prep5Answer)
        val prep5Btn = findViewById<Button>(R.id.prep5Btn)

        prep5Btn.setOnClickListener {
            if (prep5Front) {
                prep5Btn.setText("Show Question")
                frontAnim.setTarget(prep5Q)
                backAnim.setTarget(prep5A)
                frontAnim.start()
                backAnim.start()
                prep5Front = false
            } else {
                prep5Btn.setText("Show Answer")
                frontAnim.setTarget(prep5A)
                backAnim.setTarget(prep5Q)
                backAnim.start()
                frontAnim.start()
                prep5Front = true
            }
        }

        val prep6Q = findViewById<TextView>(R.id.prep6Question)
        val prep6A =findViewById<TextView>(R.id.prep6Answer)
        val prep6Btn = findViewById<Button>(R.id.prep6Btn)

        prep6Btn.setOnClickListener {
            if (prep6Front) {
                prep6Btn.setText("Show Question")
                frontAnim.setTarget(prep6Q)
                backAnim.setTarget(prep6A)
                frontAnim.start()
                backAnim.start()
                prep6Front = false
            } else {
                prep6Btn.setText("Show Answer")
                frontAnim.setTarget(prep6A)
                backAnim.setTarget(prep6Q)
                backAnim.start()
                frontAnim.start()
                prep6Front = true
            }
        }
    }
}