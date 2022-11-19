package com.example.proyecto1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameSettingsDao {

    @Insert
    fun insertGameSettings(gameSettings: GameSettings)

    @Update
    fun updateGameSettings(gameSettings: GameSettings)

    @Query("SELECT status FROM gameSettings WHERE id = :id")
    fun getGameSettingsStatusId(id: Int): Int
}