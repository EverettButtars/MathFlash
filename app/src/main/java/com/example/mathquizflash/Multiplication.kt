package com.example.mathquizflash

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class Multiplication : AppCompatActivity(), View.OnClickListener {
    lateinit var questionTextView: TextView
    lateinit var first: TextView
    lateinit var second: TextView
    lateinit var third: TextView
    lateinit var fourth: TextView
    lateinit var submit:Button
    var score = 0
    var totalQuestion:Int = MultiplicationQuestionsAnswers.question.size
    var currentQuestionIndex = 0
    var selectedAnswer = ""

    lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiplication)

        questionTextView = findViewById(R.id.question)
        first = findViewById(R.id.first)
        second = findViewById(R.id.second)
        third = findViewById(R.id.third)
        fourth = findViewById(R.id.fourth)
        submit = findViewById(R.id.submit)

        first.setOnClickListener(this)
        second.setOnClickListener(this)
        third.setOnClickListener(this)
        fourth.setOnClickListener(this)
        submit.setOnClickListener(this)
        loadNewQuestion()

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, ChooseMath::class.java)
            // start your next activity
            startActivity(intent)
        }

    }


    override fun onClick( view: View) {
        first.setBackgroundColor(Color.parseColor("#FF3700B3"))
        second.setBackgroundColor(Color.parseColor("#FF3700B3"))
        third.setBackgroundColor(Color.parseColor("#FF3700B3"))
        fourth.setBackgroundColor(Color.parseColor("#FF3700B3"))


        first.setTextColor(Color.WHITE)
        second.setTextColor(Color.WHITE)
        third.setTextColor(Color.WHITE)
        fourth.setTextColor(Color.WHITE)

        val clickedButton = view as Button
        if (clickedButton.id == R.id.submit){
            if ( selectedAnswer == MultiplicationQuestionsAnswers.correctAnswers[currentQuestionIndex]){
                score ++
            }
            currentQuestionIndex ++
            loadNewQuestion()
        }else{
            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.parseColor("#800020"))
            clickedButton.setTextColor(Color.WHITE)

        }

    }
    private fun loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion){
            finishQuiz()
            return
        }
        questionTextView.text = MultiplicationQuestionsAnswers.question[currentQuestionIndex]
        first.text = MultiplicationQuestionsAnswers.choices[currentQuestionIndex][0]
        second.text = MultiplicationQuestionsAnswers.choices[currentQuestionIndex][1]
        third.text = MultiplicationQuestionsAnswers.choices[currentQuestionIndex][2]
        fourth.text = MultiplicationQuestionsAnswers.choices[currentQuestionIndex][3]
    }
    private fun finishQuiz(){
        var passStatus = ""
        passStatus = if (score > totalQuestion * 0.60){
            "Passed "
        }else{
            "Failed"
        }
        fun createDialog() {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Pass Status")
            alertDialogBuilder.setMessage("Score is $score out of $totalQuestion")
            alertDialogBuilder.setPositiveButton("Restart") { dialogInterface: DialogInterface, i: Int ->
                restartQuiz()
            }
            alertDialogBuilder.setCancelable(false)

            alertDialogBuilder.show()
        }
        createDialog()
    }

    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }



}
