package com.example.proyecto1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selectedQuestions")
data class SelectedQuestions(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "correctAnswer") val correctAnswer: String?,
    @ColumnInfo(name = "allAnswers0") val allAnswers0: String?,
    @ColumnInfo(name = "allAnswers1") val allAnswers1: String?,
    @ColumnInfo(name = "allAnswers2") val allAnswers2: String?,
    @ColumnInfo(name = "allAnswers3") val allAnswers3: String?,
    @ColumnInfo(name = "answered") var answered: Boolean? = false,
    @ColumnInfo(name = "correctAnswered") var correctAnswered: Int? = 2,
    @ColumnInfo(name = "difficulty") var difficulty: Int? = 2,
    @ColumnInfo(name = "usedHints") var usedHints: Int? = 0,
    @ColumnInfo(name = "bUsedHints") var bUsedHints: Boolean? = false
)
