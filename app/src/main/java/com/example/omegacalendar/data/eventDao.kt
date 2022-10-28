package com.example.omegacalendar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *  lets us interface with the sqlite db easily through the dao to query and \
 *  interface with entities that are added or retrived from db
 */
@Dao
interface eventDao {
    //add queries here
    @Query ("SELECT * FROM events ORDER BY month")
    fun getEvents() : List<Event>

    @Query("SELECT * FROM events WHERE id = :eid")
    fun getEvent(eid: Int): Event

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event)

    @Query ("SELECT * FROM events WHERE month = :mon")
    fun getEventsByMonth(mon: Int): List<Event>

}