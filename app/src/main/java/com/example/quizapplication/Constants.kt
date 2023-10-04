package com.example.quizapplication

object Constants {

    fun getQuestions(): ArrayList<Questions> {
        val questionList: ArrayList<Questions> = ArrayList()

        questionList.add(Questions(1, "What it the world's largest river?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", 1))
        questionList.add(Questions(2, "What it the world's largest river1?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", 1))
        questionList.add(Questions(3, "What it the world's largest river2?", R.drawable.ic_launcher_foreground, "Nile", "Ganga", "Brahamaputra", "Yamuna", 1))
        return questionList
    }
}