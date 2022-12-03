package com.example.mathquizflash

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

class Addition : AppCompatActivity(),View.OnClickListener {
    lateinit var questionTextView: TextView
    lateinit var first: TextView
    lateinit var second: TextView
    lateinit var third: TextView
    lateinit var fourth: TextView
    lateinit var submit:Button
    var score = 0
    var answer = 0
    var totalQuestion:Int = AdditionQuestionsAnswers.question.size
    var currentQuestionIndex = 0
    var selectedAnswer = ""
    lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addition)
        questionTextView = findViewById(R.id.question)
        first = findViewById(R.id.first)
        second = findViewById(R.id.second)
        third = findViewById(R.id.third)
        fourth = findViewById(R.id.fourth)
        submit = findViewById(R.id.submit)
        back = findViewById(R.id.back)

        first.setOnClickListener(this)
        second.setOnClickListener(this)
        third.setOnClickListener(this)
        fourth.setOnClickListener(this)
        submit.setOnClickListener(this)

        loadNewQuestion()

        back.setOnClickListener {
            val intent = Intent(this, ChooseMath::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
override fun onClick( view: View) {
        first.setBackgroundColor(Color.parseColor("#2240E1"))
        second.setBackgroundColor(Color.parseColor("#2240E1"))
        third.setBackgroundColor(Color.parseColor("#2240E1"))
        fourth.setBackgroundColor(Color.parseColor("#2240E1"))

        first.setTextColor(Color.WHITE)
        second.setTextColor(Color.WHITE)
        third.setTextColor(Color.WHITE)
        fourth.setTextColor(Color.WHITE)

        val clickedButton = view as Button
        if (clickedButton.id == R.id.submit){
            if ( selectedAnswer == answer.toString()){
                score ++
                // Message if the question was correct
                val toast = Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast = Toast.makeText(applicationContext, "Incorrect", Toast.LENGTH_SHORT)
                toast.show()
            }
            currentQuestionIndex ++
            answer = loadNewQuestion()
        }else{
            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.parseColor("#DA9022"))
            clickedButton.setTextColor(Color.WHITE)

        }

    }
    private fun loadNewQuestion() : Int{
        if (currentQuestionIndex == totalQuestion){
            finishQuiz()
            return 0
        }

        val min = 0
        val max = 10
        val firstPart = (min..max).random()
        val secondPart = (min..max).random()
        answer = firstPart + secondPart
        var randomAnswer1 = (min..max + max).random()
        var randomAnswer2 = (min..max + max).random()
        var randomAnswer3 = (min..max + max).random()
        while ((randomAnswer1 == randomAnswer2) or (randomAnswer1 == randomAnswer3) or (randomAnswer1 == answer) or (randomAnswer2 == answer) or (randomAnswer3 == answer) or (randomAnswer2 == randomAnswer3)) {
            randomAnswer1 = (min..max + max).random()
            randomAnswer2 = (min..max + max).random()
            randomAnswer3 = (min..max + max).random()
        }
        val questions = listOf(answer, randomAnswer1, randomAnswer2, randomAnswer3).toMutableList()
        questions.shuffle()
        questionTextView.text = "${firstPart.toString()} + ${secondPart.toString()}"
        first.text = questions[0].toString()
        second.text = questions[1].toString()
        third.text = questions[2].toString()
        fourth.text = questions[3].toString()
        return answer
    }
    private fun finishQuiz(){
        var passStatus = ""
        passStatus = if (score > totalQuestion * 0.60){
            "Passed!"
        }else{
            "Failed"
        }
        fun createDialog() {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(passStatus)
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