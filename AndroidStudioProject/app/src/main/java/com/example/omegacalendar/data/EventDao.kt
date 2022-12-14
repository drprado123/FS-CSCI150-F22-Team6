package com.example.omegacalendar.data

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 *  lets us interface with the sqlite db easily through the dao to query and \
 *  interface with entities that are added or retrieved from db
 */
// Use DAO to interface with app database and interact with DAO for queries and persist changes back to database
// DAO converts database results into event objects that we can interact with in the app. 
@Dao
interface EventDao {
    // functions implemented in viewmodel for easy access and reuse, we utilize coroutines
    // insert, delete, and update are queries that are predefined by ROOMDB
    @Insert
    suspend fun insertEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    //add queries here
    // @Query is an input to ROOMDB that takes a string in SQL format and binds that parsed sql call to the kotlin function so when the kotlin function is called sql is executed
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
    @Query ("SELECT * FROM events_table where event_month = :mon AND event_day = :day AND event_year = :year ORDER BY event_start_hour ASC")
    fun getEventsByDayOrdered(mon: Int, day: Int, year: Int):LiveData<List<Event>>


}
