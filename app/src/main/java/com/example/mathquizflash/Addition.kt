package com.example.mathquizflash

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Addition : AppCompatActivity(),View.OnClickListener {
    private lateinit var questionTextView: TextView
    lateinit var first: TextView
    lateinit var second: TextView
    lateinit var third: TextView
    lateinit var fourth: TextView
    lateinit var submit:Button
    private var score = 0
    private var answer = 0
    private var totalQuestion:Int = AdditionQuestionsAnswers.question.size
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""
    lateinit var back: Button

    private val min = 0 // Minimum number to start questions
    private val max = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        // Receive value from choose math
        val operation=intent.getStringExtra("Operation").toString()
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

        loadNewQuestion(operation)

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
            val operation=intent.getStringExtra("Operation").toString()
            answer = loadNewQuestion(operation)
        } else{
            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.parseColor("#DA9022"))
            clickedButton.setTextColor(Color.WHITE)

        }

    }
    @SuppressLint("SetTextI18n")
    private fun loadNewQuestion(operation:String) : Int{
        if (currentQuestionIndex == totalQuestion){
            finishQuiz(operation)
            return 0
        }

        val firstPart = (min..max).random()
        val secondPart = (min..max).random()
        var randomAnswer1 = 0
        var randomAnswer2 = 0
        var randomAnswer3 = 0
        // Depends on operation


        if (operation == "add"){
            questionTextView.text = "$firstPart + $secondPart"
            answer = firstPart + secondPart
            while ((randomAnswer1 == randomAnswer2) or (randomAnswer1 == randomAnswer3) or (randomAnswer1 == answer) or (randomAnswer2 == answer) or (randomAnswer3 == answer) or (randomAnswer2 == randomAnswer3)) {
                randomAnswer1 = (min + min..max + max).random()
                randomAnswer2 = (min + min..max + max).random()
                randomAnswer3 = (min + min..max + max).random()
            }
        }
        else if (operation == "subtract"){
            questionTextView.text = "$firstPart - $secondPart"
            answer = firstPart - secondPart
            while ((randomAnswer1 == randomAnswer2) or (randomAnswer1 == randomAnswer3) or (randomAnswer1 == answer) or (randomAnswer2 == answer) or (randomAnswer3 == answer) or (randomAnswer2 == randomAnswer3)) {
                randomAnswer1 = (-max..max).random()
                randomAnswer2 = (-max..max).random()
                randomAnswer3 = (-max..max).random()
            }
        } else if (operation == "multiply") {
            questionTextView.text = "$firstPart x $secondPart"
            answer = firstPart * secondPart
            while ((randomAnswer1 == randomAnswer2) or (randomAnswer1 == randomAnswer3) or (randomAnswer1 == answer) or (randomAnswer2 == answer) or (randomAnswer3 == answer) or (randomAnswer2 == randomAnswer3)) {
                randomAnswer1 = (min..max*max).random()
                randomAnswer2 = (min..max*max).random()
                randomAnswer3 = (min..max*max).random()
            }
        } else if (operation == "divide") {
            questionTextView.text = "$firstPart / $secondPart"
            answer = firstPart / secondPart
            while ((randomAnswer1 == randomAnswer2) or (randomAnswer1 == randomAnswer3) or (randomAnswer1 == answer) or (randomAnswer2 == answer) or (randomAnswer3 == answer) or (randomAnswer2 == randomAnswer3)) {
                randomAnswer1 = (min..max).random()
                randomAnswer2 = (min..max).random()
                randomAnswer3 = (min..max).random()
            }
        }
        else {
            questionTextView.text = "$firstPart ? $secondPart"
            val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
            toast.show()
        }


        val questions = listOf(answer, randomAnswer1, randomAnswer2, randomAnswer3).toMutableList()
        questions.shuffle()

        first.text = questions[0].toString()
        second.text = questions[1].toString()
        third.text = questions[2].toString()
        fourth.text = questions[3].toString()
        return answer
    }
    private fun finishQuiz(operation:String){

        val passStatus = if (score > totalQuestion * 0.60){
            "Passed!"
        }else{
            "Failed"
        }
        fun createDialog() {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(passStatus)
            alertDialogBuilder.setMessage("Score is $score out of $totalQuestion")
            alertDialogBuilder.setPositiveButton("Restart") { _: DialogInterface, _: Int ->
                restartQuiz(operation)
            }
            alertDialogBuilder.setCancelable(false)

            alertDialogBuilder.show()
        }
        createDialog()
    }

    private fun restartQuiz(operation:String) {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion(operation)
    }

}