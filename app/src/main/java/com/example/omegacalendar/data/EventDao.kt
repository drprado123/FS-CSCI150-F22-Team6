package com.example.omegacalendar.data

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 *  lets us interface with the sqlite db easily through the dao to query and \
 *  interface with entities that are added or retrieved from db
 */
@Dao
interface EventDao {
    // functions implemented in viewmodel for easy access and reuse, we utilize coroutines
    @Insert
    suspend fun insertEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    //add queries here
    @Query("SELECT * FROM events_table ORDER BY event_year")
    fun getEvents() : LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_id = :eid")
    fun getEvent(eid: Int): Event

    @Query ("SELECT * FROM events_table WHERE event_month = :mon AND event_year = :year")
    fun getEventsByMonth(mon: Int, year: Int): LiveData<List<Event>>
    @Query("SELECT * FROM events_table where event_month = :mon")
    fun getMonths(mon: Int): LiveData<List<Event>>
    @Query ("SELECT * FROM events_table where event_month = :mon AND event_day = :day AND event_year = :year")
    fun getEventsByDay(mon: Int, day: Int, year: Int):LiveData<List<Event>>
    @Query ("SELECT * FROM events_table where event_month = :mon AND event_day = :day AND event_year = :year ORDER BY event_start_hour DESC")
    fun getEventsByDayOrdered(mon: Int, day: Int, year: Int):LiveData<List<Event>>


}