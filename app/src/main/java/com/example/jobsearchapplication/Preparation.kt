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
    var isFront = true
    var prep2Front = true
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
        val front = findViewById<TextView>(R.id.card_front) as TextView
        val back =findViewById<TextView>(R.id.card_back) as TextView
        val flip = findViewById<Button>(R.id.flip_btn) as Button

        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        flip.setOnClickListener {
            if (isFront) {
                flip.setText("Show Question")
                front_anim.setTarget(front)
                back_anim.setTarget(back)
                front_anim.start()
                back_anim.start()
                isFront = false

            } else {
                flip.setText("Show Answer")
                front_anim.setTarget(back)
                back_anim.setTarget(front)
                back_anim.start()
                front_anim.start()
                isFront = true

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
    }
}