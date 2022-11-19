package com.example.proyecto1

data class Question(
    val text: String,
    val theme: String,
    val correctAnswer: String,
    val allAnswers: ArrayList<String>,
    var answered: Boolean = false,
    var correctAnswered: Int = 2,
    var difficulty: Int = 2,
    var usedHints: Int = 0,
    var fUsedHints: Boolean = false
)
