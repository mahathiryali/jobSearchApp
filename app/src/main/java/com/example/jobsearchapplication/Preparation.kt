package com.example.jobsearchapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class Preparation : AppCompatActivity() {
    lateinit var front_anim:AnimatorSet
    lateinit var back_anim: AnimatorSet
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, Resources::class.java)
            startActivity(intent)
        }

        var scale = applicationContext.resources.displayMetrics.density
        val prep1Q = findViewById<TextView>(R.id.prep1Question) as TextView
        val prep1A =findViewById<TextView>(R.id.prep1Answer) as TextView
        val prep1Btn = findViewById<Button>(R.id.prep1Btn) as Button
        prep1Q.cameraDistance = 8000 * scale
        prep1A.cameraDistance = 8000 * scale
        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        prep1Btn.setOnClickListener {
            if (prep1Front) {
                prep1Btn.setText("Show Question")
                front_anim.setTarget(prep1Q)
                back_anim.setTarget(prep1A)
                front_anim.start()
                back_anim.start()
                prep1Front = false
            } else {
                prep1Btn.setText("Show Answer")
                front_anim.setTarget(prep1A)
                back_anim.setTarget(prep1Q)
                back_anim.start()
                front_anim.start()
                prep1Front = true
            }
        }

        val prep2Q = findViewById<TextView>(R.id.prep2Question) as TextView
        val prep2A =findViewById<TextView>(R.id.prep2Answer) as TextView
        val prep2Btn = findViewById<Button>(R.id.prep2Btn) as Button

        prep2Btn.setOnClickListener {
            if (prep2Front) {
                prep2Btn.setText("Show Question")
                front_anim.setTarget(prep2Q)
                back_anim.setTarget(prep2A)
                front_anim.start()
                back_anim.start()
                prep2Front = false
            } else {
                prep2Btn.setText("Show Answer")
                front_anim.setTarget(prep2A)
                back_anim.setTarget(prep2Q)
                back_anim.start()
                front_anim.start()
                prep2Front = true
            }
        }

        val prep3Q = findViewById<TextView>(R.id.prep3Question) as TextView
        val prep3A =findViewById<TextView>(R.id.prep3Answer) as TextView
        val prep3Btn = findViewById<Button>(R.id.prep3Btn) as Button

        prep3Btn.setOnClickListener {
            if (prep3Front) {
                prep3Btn.setText("Show Question")
                front_anim.setTarget(prep3Q)
                back_anim.setTarget(prep3A)
                front_anim.start()
                back_anim.start()
                prep3Front = false
            } else {
                prep3Btn.setText("Show Answer")
                front_anim.setTarget(prep3A)
                back_anim.setTarget(prep3Q)
                back_anim.start()
                front_anim.start()
                prep3Front = true
            }
        }

        val prep4Q = findViewById<TextView>(R.id.prep4Question) as TextView
        val prep4A =findViewById<TextView>(R.id.prep4Answer) as TextView
        val prep4Btn = findViewById<Button>(R.id.prep4Btn) as Button

        prep4Btn.setOnClickListener {
            if (prep4Front) {
                prep4Btn.setText("Show Question")
                front_anim.setTarget(prep4Q)
                back_anim.setTarget(prep4A)
                front_anim.start()
                back_anim.start()
                prep4Front = false
            } else {
                prep4Btn.setText("Show Answer")
                front_anim.setTarget(prep4A)
                back_anim.setTarget(prep4Q)
                back_anim.start()
                front_anim.start()
                prep4Front = true
            }
        }

        val prep5Q = findViewById<TextView>(R.id.prep5Question) as TextView
        val prep5A =findViewById<TextView>(R.id.prep5Answer) as TextView
        val prep5Btn = findViewById<Button>(R.id.prep5Btn) as Button

        prep5Btn.setOnClickListener {
            if (prep5Front) {
                prep5Btn.setText("Show Question")
                front_anim.setTarget(prep5Q)
                back_anim.setTarget(prep5A)
                front_anim.start()
                back_anim.start()
                prep5Front = false
            } else {
                prep5Btn.setText("Show Answer")
                front_anim.setTarget(prep5A)
                back_anim.setTarget(prep5Q)
                back_anim.start()
                front_anim.start()
                prep5Front = true
            }
        }

        val prep6Q = findViewById<TextView>(R.id.prep6Question) as TextView
        val prep6A =findViewById<TextView>(R.id.prep6Answer) as TextView
        val prep6Btn = findViewById<Button>(R.id.prep6Btn) as Button

        prep6Btn.setOnClickListener {
            if (prep6Front) {
                prep6Btn.setText("Show Question")
                front_anim.setTarget(prep6Q)
                back_anim.setTarget(prep6A)
                front_anim.start()
                back_anim.start()
                prep6Front = false
            } else {
                prep6Btn.setText("Show Answer")
                front_anim.setTarget(prep6A)
                back_anim.setTarget(prep6Q)
                back_anim.start()
                front_anim.start()
                prep6Front = true
            }
        }
    }
}