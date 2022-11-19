package com.example.proyecto1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Score::class, Questions::class, GameSettings::class, GameSet::class, SelectedQuestions::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
    abstract fun questionsDao(): QuestionsDao
    abstract fun gameSettingsDao(): GameSettingsDao
    abstract fun gameSetDao(): GameSetDao
    abstract fun selectedQuestionsDao(): SelectedQuestionsDao
}