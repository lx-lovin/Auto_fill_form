package com.example.autofill

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_booking.*
import android.webkit.ValueCallback
import android.os.Build
import android.webkit.WebView
import android.support.v4.os.HandlerCompat.postDelayed
import java.util.logging.Handler


class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        var username:String = intent.getStringExtra("username")
        var password:String = intent.getStringExtra("password")
        var from:String = intent.getStringExtra("from")
        var to:String = intent.getStringExtra("to")
        var date:String = intent.getStringExtra("date")
        var name:String = intent.getStringExtra("name")
        var age:Int = intent.getIntExtra("age",10)


        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.irctc.co.in/nget/train-search")

        var login:String = "javascript:document.getElementById('loginText').click();"+"javascript:document.getElementById('userId').value='"+username+"';"+"javascript:document.getElementById('pwd').value='"+password+"';"

        var js1:String = "javascript:document.getElementById('userId').value='hello'"


        var journey:String = "javascript:document.querySelectorAll('input.ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ui-autocomplete-input.ng-star-inserted')[0].value='"+from+"';"+
                "javascript:document.querySelectorAll('input.ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ui-autocomplete-input.ng-star-inserted')[1].value='"+to+"';"+
                "javascript:document.querySelectorAll('input.ng-tns-c14-6.ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted')[0].value='"+date+"';"

        var trainselect:String = "javascript:document.getElementById('check-availability').click();"+
                "javascript:document.querySelectorAll('button.b1')[0].click();"

        var passanger:String ="javascript:document.getElementById('psgn-name').value='"+name+"';"+
                "javascript:document.querySelectorAll('input.form-control.ng-dirty.ng-valid.ng-touched')[0].value='"+age+"';"

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (Build.VERSION.SDK_INT >= 19) {
                    view.evaluateJavascript(login) {

                    }
                    val handler = android.os.Handler()
                    handler.postDelayed(Runnable {
                        view.evaluateJavascript(journey){

                        }

                        val handler = android.os.Handler()
                        handler.postDelayed(Runnable {
                            view.evaluateJavascript(trainselect){

                            }

                            val handler = android.os.Handler()
                            handler.postDelayed(Runnable {
                                view.evaluateJavascript(passanger){

                                }

                            }, 10000)

                        }, 20000)
                    }, 30000)

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
