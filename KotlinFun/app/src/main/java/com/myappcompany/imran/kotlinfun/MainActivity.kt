package com.myappcompany.imran.kotlinfun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count = 0

    fun reset(view : View) {
        count = 0
        //textView.setText(count.toString())
        textView.text = count.toString() //In kotlin alternate option
    }

    fun plusOne(view : View) {
        count++
        //textView.setText(count.toString())
        textView.text = count.toString() //In kotlin alternate option
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textView)
        //textView.setText("Hello Fam!")
        //textView.text = "Hello Fam!"
        textView.text = "0"
    }
}