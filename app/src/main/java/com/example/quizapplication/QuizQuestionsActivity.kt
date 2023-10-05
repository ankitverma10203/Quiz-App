package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionList: ArrayList<Questions>? = null
    private var question: TextView? = null
    private var image: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var progressText: TextView? = null
    private var option1: TextView? = null
    private var option2: TextView? = null
    private var option3: TextView? = null
    private var option4: TextView? = null
    private var optionList: Map<Int?, TextView?>? = null
    private var currentQuestionNumber = 1
    private var submitBtn: Button? = null
    private var score: Int = 0
    private var selectedOption: View? = null
    private var answered: Boolean = false
    private var currentQuestion: Questions? = null
    private var correctOption: View? = null
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME_KW)

        question = findViewById(R.id.question)
        image = findViewById(R.id.image)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_value)
        option1 = findViewById(R.id.option1)
        option1?.setOnClickListener(this)
        option2 = findViewById(R.id.option2)
        option2?.setOnClickListener(this)
        option3 = findViewById(R.id.option3)
        option3?.setOnClickListener(this)
        option4 = findViewById(R.id.option4)
        option4?.setOnClickListener(this)

        optionList = mapOf(
            option1?.id to (option1 as TextView),
            option2?.id to (option2 as TextView),
            option3?.id to (option3 as TextView),
            option4?.id to (option4 as TextView)
        )

        submitBtn = findViewById(R.id.btn_submit)

        questionList = Constants.getQuestions()
        getQuestions()

        submitBtn?.setOnClickListener {
            submitOnClickHandler()
        }
    }

    private fun getQuestions() {
        resetOptionsStyle()
        currentQuestion = questionList!![currentQuestionNumber - 1]
        question?.text = currentQuestion?.question
        currentQuestion?.image?.let { image?.setImageResource(it) }

        progressBar?.max = questionList!!.size
        progressBar?.progress = currentQuestionNumber
        progressText?.text = "$currentQuestionNumber/${questionList?.size}"

        option1?.text = currentQuestion?.option1
        option2?.text = currentQuestion?.option2
        option3?.text = currentQuestion?.option3
        option4?.text = currentQuestion?.option4

        if (currentQuestionNumber == questionList!!.size) {
            submitBtn?.text = "FINISH"
        }

        correctOption = getCorrectOption()
    }

    private fun getCorrectOption(): TextView? {
        for (option in optionList!!.values) {
            if (option?.text == currentQuestion?.ans) {
                return option
            }
        }
        return null
    }

    private fun resetOptionsStyle() {
        selectedOption = null
        answered = false
        optionList.let {
            for (option in optionList!!.values) {
                option?.background = getDrawable(R.drawable.normal_option_background)
            }
        }
    }

    private fun submitOnClickHandler() {
        if (answered && currentQuestionNumber < questionList!!.size) {
            currentQuestionNumber++
            getQuestions()
        } else if (answered) {
            resetOptionsStyle()
            val intent: Intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra(Constants.USER_NAME_KW, userName)
            intent.putExtra(Constants.SCORE_KW, score)
            intent.putExtra(Constants.TOTAL_QUESTIONS_KW, questionList?.size)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option1, R.id.option2, R.id.option3, R.id.option4 -> {
                if (answered) return
                selectedOption = view
                checkAnswer()
                answered = true
            }
        }
    }

    private fun checkAnswer() {
        if((selectedOption as TextView).text == currentQuestion?.ans) {
            selectedOption?.background = getDrawable(R.drawable.correct_option_background)
            score++
            Toast.makeText(this,"Correct Answer!", Toast.LENGTH_SHORT).show()
        } else {
            selectedOption?.background = getDrawable(R.drawable.incorrect_option_background)
            correctOption?.background = getDrawable(R.drawable.correct_option_background)
            Toast.makeText(this,"Wrong Answer!", Toast.LENGTH_SHORT).show()
        }
    }

}