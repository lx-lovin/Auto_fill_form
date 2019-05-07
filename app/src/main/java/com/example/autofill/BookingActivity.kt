package com.example.autofill

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_booking.*
import android.webkit.ValueCallback
import android.os.Build
import android.webkit.WebView




class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        var username:String = intent.getStringExtra("username")
        var password:String = intent.getStringExtra("password")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.irctc.co.in/nget/train-search")

        var js:String = "javascript:document.getElementById('loginText').click();"+"javascript:document.getElementById('userId').value='"+username+"';"+"javascript:document.getElementById('pwd').value='"+password+"';"

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (Build.VERSION.SDK_INT >= 19) {
                    view.evaluateJavascript(js) {

                    }
                }
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}
