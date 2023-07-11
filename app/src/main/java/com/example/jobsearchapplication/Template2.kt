package com.example.jobsearchapplication

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Template2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.templates)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://drive.google.com/file/d/1eT7CVwYqnL5AXiYk-4E4PFtQrXJDB95K/view?usp=drive_link")

        val backBtn = findViewById<Button>(R.id.back)
        backBtn.setOnClickListener {
            val intent = Intent(this, Resumes::class.java)
            startActivity(intent)
        }
    }
}