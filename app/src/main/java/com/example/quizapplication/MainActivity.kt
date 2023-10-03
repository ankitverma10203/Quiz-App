package com.example.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<TextInputLayout>(R.id.name_input_layout)
        var name = input.editText?.text.toString()

        val submit = findViewById<Button>(R.id.btn_submit)

        input.editText?.doOnTextChanged{text, _, _, _ -> name = text.toString() }

        submit.setOnClickListener {
            handleSubmit(name)
        }
    }

    fun handleSubmit(name: String) {
        println(name)
        val text = findViewById<TextView>(R.id.welcome)
        text.text = "Welcome $name!"
    }
}