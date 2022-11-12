package com.example.mathquizflash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {
    lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}