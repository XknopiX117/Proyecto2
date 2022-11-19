package com.example.proyecto1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Dao
interface GameSetDao{

    @Insert
    fun insertGameSet(gameSet: GameSet)

    @Query("SELECT * FROM gameSet")
    fun getAllGameSet(): List<GameSet>

    @Query("SELECT status FROM gameSet WHERE id < 11")
    fun getAllStatusGameSet(): List<Int>

    @Query("SELECT status FROM gameSet WHERE id = 97")
    fun getActualScoreGameSet(): Int

    @Query("SELECT * FROM gameSet WHERE id = 97")
    fun getScoreGameSet(): GameSet

    @Query("SELECT status FROM gameSet WHERE id = 98")
    fun getActualIndexGameSet(): Int

    @Query("SELECT status FROM gameSet WHERE id = 99")
    fun getActualHintsGameSet(): Int

    @Query("SELECT * FROM gameSet WHERE id = 99")
    fun getHintsGameSet(): GameSet

    @Query("SELECT status FROM gameSet WHERE id = 100")
    fun getActualHintsUsedGameSet(): Int

    @Query("SELECT * FROM gameSet WHERE id = 100")
    fun getHintsUsedGameSet(): GameSet

    @Query("SELECT status FROM gameSet WHERE id = 101")
    fun getActualHintStreakGameSet(): Int

    @Query("SELECT * FROM gameSet WHERE id = 101")
    fun getHintStreakGameSet(): GameSet

    @Query("SELECT COUNT(*) FROM gameSet")
    fun getAllNGameSet(): Int

    @Query("DELETE FROM gameSet")
    fun deleteGameSet()

    @Update
    fun updateGameSet(gameSet: GameSet)
}