package com.example.omegacalendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
data class Event (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    var event_id: Int,
    @ColumnInfo(name = "event_year")
    var year: Int,
    @ColumnInfo(name = "event_month")
    var month: Int,
    @ColumnInfo(name = "event_day")
    var day: Int,
    @ColumnInfo(name = "event_description")
    var desc: String,
    @ColumnInfo(name = "event_start")
    var startTime: Int,
    @ColumnInfo(name = "event_end")
    var endTime: Int //add additional attribute to an event
) {
    //function space
}