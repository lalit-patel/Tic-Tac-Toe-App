package com.patel.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstbtn.setOnClickListener {
            val i = Intent(this,First::class.java)
            startActivity(i)
        }

        secbtn.setOnClickListener {
            val i = Intent(this,Second::class.java)
            startActivity(i)
        }

    }
}