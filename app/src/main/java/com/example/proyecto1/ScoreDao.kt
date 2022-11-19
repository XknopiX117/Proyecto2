package com.example.proyecto1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert
    fun insertScore(score: Score)

    @Query("SELECT COUNT(*) FROM score")
    fun getNScore(): Int

    @Query("SELECT * FROM score ORDER BY points DESC")
    fun getAllScore(): List<Score>

    @Query("SELECT * FROM score ORDER BY points DESC LIMIT 5")
    fun getTopFiveScore(): List<Score>
}