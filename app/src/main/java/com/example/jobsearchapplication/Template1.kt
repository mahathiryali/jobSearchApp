package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Template1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.templates)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://drive.google.com/file/d/1rVILjMngP-eCEOY_WJKdoxbyFoytIfWh/view?usp=drive_link")

        val backBtn = findViewById<Button>(R.id.back)
        backBtn.setOnClickListener {
            val intent = Intent(this, Resumes::class.java)
            startActivity(intent)
        }
    }
}