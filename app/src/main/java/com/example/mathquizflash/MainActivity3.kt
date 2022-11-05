package com.example.mathquizflash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {

    lateinit var back: Button
    lateinit var add: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            // start your next activity
            startActivity(intent)
        }

        add = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            // start your next activity
            startActivity(intent)
        }
    }

}