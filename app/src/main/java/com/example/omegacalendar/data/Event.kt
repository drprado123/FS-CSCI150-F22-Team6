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
    @ColumnInfo(name = "event_start_hour")
    var startHour: Int, //add additiona
    @ColumnInfo(name = "event_start_min")
    var startMin: Int,
    @ColumnInfo(name = "event_end_hour")
    var endHour: Int, //add additional attribute to an event
    @ColumnInfo(name = "event_end_min")
    var endMin: Int,
    @ColumnInfo(name = "calendar_name")
    var calNam: String
)
    //function space
{

}