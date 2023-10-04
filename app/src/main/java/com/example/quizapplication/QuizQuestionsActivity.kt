package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {

    var questionList: ArrayList<Questions>? = null
    var question: TextView? = null
    var image: ImageView? = null
    var progressBar: ProgressBar? = null
    var progressText: TextView? = null
    var option1: TextView? = null
    var option2: TextView? = null
    var option3: TextView? = null
    var option4: TextView? = null
    var optionList: ArrayList<TextView>? = null
    var currentQuestion = 1
    var submitBtn: Button? = null
    var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        question = findViewById(R.id.question)
        image = findViewById(R.id.image)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_value)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)

        optionList = ArrayList()
        optionList?.add(option1 as TextView)
        optionList?.add(option2 as TextView)
        optionList?.add(option3 as TextView)
        optionList?.add(option4 as TextView)

        submitBtn = findViewById(R.id.btn_submit)

        questionList = Constants.getQuestions()
        getQuestions()

        submitBtn?.setOnClickListener {
            submitOnClickHandler()
        }

    }

    private fun getQuestions() {

        question?.text = questionList!![currentQuestion - 1].question
        image?.setImageResource(questionList!![currentQuestion - 1].image)

        progressBar?.max = questionList!!.size
        progressBar?.progress = currentQuestion
        progressText?.text = "$currentQuestion/${questionList?.size}"

        option1?.text = questionList!![currentQuestion - 1].option1
        option2?.text = questionList!![currentQuestion - 1].option2
        option3?.text = questionList!![currentQuestion - 1].option3
        option4?.text = questionList!![currentQuestion - 1].option4

        if (currentQuestion == questionList!!.size) {
            submitBtn?.text = "FINISH"
        }
    }

    private fun resetOptionsStyle() {
        optionList.let {
            for (option in optionList!!) {
                option.background = getDrawable(R.drawable.normal_option_background)
            }
        }
    }

    private fun submitOnClickHandler() {

        if (currentQuestion < questionList!!.size) {
            currentQuestion++
            getQuestions()
        } else {
            val intent: Intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}