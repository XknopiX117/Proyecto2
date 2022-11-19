package com.example.proyecto1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameSettings")
data class GameSettings(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "status") val status: Int?
)
