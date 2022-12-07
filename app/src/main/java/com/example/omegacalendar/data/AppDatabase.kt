package com.example.omegacalendar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//create the database using room so we can access Events and store them database on first launch of the app maintained and accessed in all subsequent startups
@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun eventDao():EventDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "event_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
