package com.example.omegacalendar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.omegacalendar.data.AppDatabase
import com.example.omegacalendar.data.Event
import com.example.omegacalendar.data.EventViewModel
import com.example.omegacalendar.data.EventViewModelFactory
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme

class AddEventActivity : AppCompatActivity() {
    private lateinit var monthText : EditText
    private lateinit var dayText : EditText
    private lateinit var yearText : EditText
    private lateinit var descText : EditText
    private lateinit var startText : EditText
    private lateinit var endText : EditText
    private lateinit var insertBtn : Button

    private lateinit var viewModel : EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_activity)

        monthText = findViewById(R.id.monthTv)
        dayText = findViewById(R.id.dayTv)
        yearText = findViewById(R.id.yearTv)
        descText = findViewById(R.id.descTv)
        startText = findViewById(R.id.startTv)
        endText = findViewById(R.id.endTv)
        insertBtn = findViewById(R.id.add_event)

        val dao = AppDatabase.getInstance(application).eventDao()
        val factory = EventViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory).get(EventViewModel::class.java)
        insertBtn.setOnClickListener {
            saveEvent()
            clearInput()
        }
    }
    private fun saveEvent() {
        val month = monthText.text.toString().toInt()
        val day = dayText.text.toString().toInt()
        val year = yearText.text.toString().toInt()
        val desc = descText.text.toString()
        val start = startText.text.toString().toInt()
        val end = endText.text.toString().toInt()

        val event = Event(0,year, month,day,desc, start, end )

        viewModel.insertEvent(event)

    }
    private fun clearInput() {
        monthText.setText("")
        dayText.setText("")
        yearText.setText("")
        descText.setText("")
        startText.setText("")
        endText.setText("")

    }
}