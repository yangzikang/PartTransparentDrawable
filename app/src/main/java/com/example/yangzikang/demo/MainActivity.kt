package com.example.yangzikang.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textview = findViewById<TextView>(R.id.textView)

        var button = findViewById<Button>(R.id.btn_click_me)
//        button.setOnClickListener(View.OnClickListener {
//            textview.setText("hello")
//        })

        button.setOnClickListener {
            startActivity(Intent(this,PopupActivity::class.java))
        }
    }
}
