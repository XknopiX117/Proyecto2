package com.example.proyecto1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SelectedQuestionsDao {
    @Insert
    fun insertSelectedQuestions(selectedQuestions: SelectedQuestions)

    @Query("SELECT * FROM selectedQuestions WHERE id = :id")
    fun getSelectedQuestionsId(id: Int): Questions

    @Query("SELECT COUNT(*) FROM selectedQuestions")
    fun getAllNSelectedQuestions(): Int

    @Query("SELECT correctAnswer FROM selectedQuestions WHERE id = :id")
    fun getCorrectAnswerId(id: Int): String

    @Query("SELECT allAnswers0 FROM selectedQuestions WHERE id = :id")
    fun getallAnswers0Id(id: Int): String

    @Query("SELECT allAnswers1 FROM selectedQuestions WHERE id = :id")
    fun getallAnswers1Id(id: Int): String

    @Query("SELECT allAnswers2 FROM selectedQuestions WHERE id = :id")
    fun getallAnswers2Id(id: Int): String

    @Query("SELECT allAnswers3 FROM selectedQuestions WHERE id = :id")
    fun getallAnswers3Id(id: Int): String

    @Query("SELECT answered FROM selectedQuestions WHERE id = :id")
    fun getAnsweredSelectedQuestions(id: Int): Boolean

    @Query("SELECT answered FROM selectedQuestions")
    fun getAllAnsweredSelectedQuestions(): List<Boolean>

    @Query("SELECT * FROM selectedQuestions WHERE id = :id")
    fun getCurrentSelectedQuestions(id: Int): SelectedQuestions

    @Query("SELECT correctAnswered FROM selectedQuestions WHERE id = :id")
    fun getCurrentCorrectAnsweredSelectedQuestions(id: Int): Int

    @Query("SELECT difficulty FROM selectedQuestions WHERE id = :id")
    fun getDifficultySelectedQuestions(id: Int): Int

    @Query("SELECT bUsedHints FROM selectedQuestions WHERE id = :id")
    fun getBUsedHintsSelectedQuestions(id: Int): Boolean

    @Update
    fun updateSelectedQuestions(selectedQuestions: SelectedQuestions)

    @Query("DELETE FROM selectedQuestions")
    fun deleteSelectedQuestions()
}