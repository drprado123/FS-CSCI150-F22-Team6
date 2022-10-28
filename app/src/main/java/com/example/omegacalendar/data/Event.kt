package com.example.omegacalendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val event_id: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val desc: String,
    val startTime: Int,
    val endTime: Int //add additional attribute to an event
) {
    //function space
}