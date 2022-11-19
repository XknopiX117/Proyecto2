package com.example.proyecto1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameSet")
data class GameSet(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "status") var status: Int?,

)
