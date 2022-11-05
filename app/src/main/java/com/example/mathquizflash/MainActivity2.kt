package com.example.mathquizflash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    lateinit var mainbtn: Button
    lateinit var oneSkillButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainbtn = findViewById(R.id.mainbtn)

        mainbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
        oneSkillButton = findViewById(R.id.OneSkillButton)
        oneSkillButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            // start your next activity
            startActivity(intent)
        }
    }


}