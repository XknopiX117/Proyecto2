package com.example.proyecto1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionsDao {

    @Insert
    fun insertQuestions(questions: Questions)

    @Query("SELECT COUNT(*) FROM questions")
    fun getAllNumberQuestions(): Int

    @Query("SELECT * FROM questions WHERE id = :id")
    fun getQuestionsData(id: Int): Questions

    @Query("SELECT text FROM questions WHERE id = :id")
    fun getQuestionsText(id: Int): String

    @Query("SELECT theme FROM questions WHERE id = :id")
    fun getQuestionsTheme(id: Int): String

    @Query("SELECT id FROM questions")
    fun getQuestionsAllThemeSelected(): List<Int>

    @Query("SELECT id FROM questions WHERE theme = :theme")
    fun getQuestionsThemeSelected(theme: String): List<Int>

    @Query("SELECT id FROM questions WHERE theme = :theme1 OR theme = :theme2")
    fun getQuestionsThemeSelected2(theme1: String, theme2: String): List<Int>

    @Query("SELECT id FROM questions WHERE theme = :theme1 OR theme = :theme2 OR theme = :theme3")
    fun getQuestionsThemeSelected3(theme1: String, theme2: String, theme3: String): List<Int>

    @Query("SELECT id FROM questions WHERE theme = :theme1 OR theme = :theme2 OR theme = :theme3 OR theme = :theme4")
    fun getQuestionsThemeSelected4(
        theme1: String,
        theme2: String,
        theme3: String,
        theme4: String
    ): List<Int>

}