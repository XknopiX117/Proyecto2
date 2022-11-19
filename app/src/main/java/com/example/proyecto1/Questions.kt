package com.example.proyecto1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Questions(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "theme") val theme: String?,
    @ColumnInfo(name = "correctAnswer") val correctAnswer: String?,
    @ColumnInfo(name = "allAnswers0") val allAnswers0: String?,
    @ColumnInfo(name = "allAnswers1") val allAnswers1: String?,
    @ColumnInfo(name = "allAnswers2") val allAnswers2: String?,
    @ColumnInfo(name = "allAnswers3") val allAnswers3: String?
)
