package com.example.mathquizflash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseMath : AppCompatActivity() {

    lateinit var back: Button
    lateinit var add: Button
    lateinit var subtract: Button
    lateinit var multiply: Button
    lateinit var divide: Button
    lateinit var fraction: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choosemath)

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            // start your next activity
            startActivity(intent)
        }

        add = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, Addition::class.java)
            // start your next activity
            startActivity(intent)
        }

        subtract = findViewById(R.id.subtract)
        subtract.setOnClickListener {
            val intent = Intent(this, Subtraction::class.java)
            // start your next activity
            startActivity(intent)
        }

        multiply = findViewById(R.id.multiply)
        multiply.setOnClickListener {
            val intent = Intent(this, Multiplication::class.java)
            // start your next activity
            startActivity(intent)
        }
        divide = findViewById(R.id.divide)
        divide.setOnClickListener {
            val intent = Intent(this, Division::class.java)
            // start your next activity
            startActivity(intent)
        }

        fraction = findViewById(R.id.fraction)
        fraction.setOnClickListener {
            val intent = Intent(this, Division2::class.java)
            // start your next activity
            startActivity(intent)
        }
    }

}