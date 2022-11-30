package com.example.omegacalendar

import android.content.Context
import android.icu.util.GregorianCalendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.omegacalendar.data.AppDatabase
import com.example.omegacalendar.data.EventViewModel
import com.example.omegacalendar.data.EventViewModelFactory
import com.example.omegacalendar.ui.MonthComponent
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme
//import com.example.omegacalendar.data.Event
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.omegacalendar.data.AppDatabase

class MainActivity : ComponentActivity() {
    private lateinit var viewModel : EventViewModel
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val context: Context = MainActivity.applicationContext()
        val dao = AppDatabase.getInstance(application).eventDao()
        //factory used to instantiate and pass dao to view model
        val factory = EventViewModelFactory(dao)
        super.onCreate(savedInstanceState)
        setContent {
            OmegaCalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //val rightNow = GregorianCalendar.getInstance()

                    OmegaCalendarApp(viewModel = ViewModelProvider(this, factory).get(EventViewModel::class.java))//MonthComponent()//(rightNow as GregorianCalendar)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OmegaCalendarTheme {

    }
}