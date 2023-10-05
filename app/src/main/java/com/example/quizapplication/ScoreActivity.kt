package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    var congratsTv : TextView? = null
    var scoreTv : TextView? = null
    var finishBtn : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        congratsTv = findViewById(R.id.congrats)
        congratsTv?.text = "Congrats ${intent.getStringExtra(Constants.USER_NAME_KW)}!"
        scoreTv = findViewById(R.id.score)
        scoreTv?.text = "Score: ${intent.getIntExtra(Constants.SCORE_KW, 0)}/${intent.getIntExtra(Constants.TOTAL_QUESTIONS_KW, 0)}"

        finishBtn = findViewById<Button>(R.id.finish)
        finishBtn?.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}