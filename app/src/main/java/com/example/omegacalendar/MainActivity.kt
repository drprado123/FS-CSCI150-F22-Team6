package com.example.omegacalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.omegacalendar.data.Datasource
import com.example.omegacalendar.model.Day
import com.example.omegacalendar.model.Events
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyScreen ()

        }
    }
}

@Composable
fun DailyScreen() {
    OmegaCalendarTheme() {
        DailySlide(EventList = Datasource().loadAffirmations())
    }
}


@Composable     // THIS IS ALL THE DISPLAY AREA
fun DailySlide(EventList: List<Events>, modifier: Modifier = Modifier) {

    Column() {

        

        LazyColumn(
            modifier = Modifier
            .weight(8/12f), )
        {
            items(EventList) { Evento ->
                EventSlide(Evento)
            }
        }
    }

}

@Composable
fun EventSlide (Evento: Events, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Row {
            if (Evento.DayId == R.string.Tue) {

            Text(   // This is Responsable of displaying the Descritption of the Event
                text = LocalContext.current.getString(Evento.EventsId),
                //textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(10 / 16f),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = LocalContext.current.getString(Evento.TimeId),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(2 / 16f),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = LocalContext.current.getString(Evento.M),
                modifier = Modifier
                    .padding(2.dp)
                    .weight(2 / 16f),
                style = MaterialTheme.typography.h6
            )

            }

        }
    }
}

@Preview
@Composable
private fun DailyScreenPreview() {
    EventSlide (Events(R.string.Event1, R.string.Time1, R.string.Mon, R.string.AM))
}