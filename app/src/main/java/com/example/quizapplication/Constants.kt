package com.example.quizapplication

object Constants {

    const val SCORE_KW = "SCORE"
    const val TOTAL_QUESTIONS_KW = "TOTAL_QUESTIONS"
    const val USER_NAME_KW = "USER_NAME"

    fun getQuestions(): ArrayList<Questions> {
        val questionList: ArrayList<Questions> = ArrayList()

        questionList.add(Questions(1, "What it the world's largest river?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", "Nile"))
        questionList.add(Questions(2, "What it the world's largest river1?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", "Ganga"))
        questionList.add(Questions(3, "What it the world's largest river2?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", "Yamuna"))
        return questionList
    }
}