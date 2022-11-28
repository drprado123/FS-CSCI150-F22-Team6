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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
@Composable     // This is just called upon to get the whole display. Imagine it as the program
fun DailyScreen() {
    OmegaCalendarTheme() {
        DailySlide(
            EventList = Datasource().loadAffirmations()

        )
    }
}


@Composable     // This is where it grabs the things to display. Such as the list along with the buttons
fun DailySlide(EventList: List<Events>, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Navigations()

        LazyColumn(modifier = Modifier
            .weight(10/12f)
        ) {
            items(EventList) { Evento ->
                EventSlide(Evento)
            }
        }
    }

}

@Composable     // This all focuses on the Top Column, Buttons Year and Month Display
fun Navigations() {

/*
    var y by remember { mutableStateOf(cal.get(calendar.YEAR)) }
    var m by remember { mutableStateOf(cal.get(Calendar.MONTH)) }
    var d by remember { mutableStateOf(cat.get(Calendar.DAY_OF_MONTH))}

    val mn = OMonth(m, y, d)

*/

    Box(modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.align(Alignment.Center)) {

            Text(
                text = "Month",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

            Text (
                text = "Day",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

            Text(
                text = "Year",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

        }


    }

}


@Composable         // This all focuses on the Bottom Column, The lazy Column Slide/ Event Displays
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


@Preview (showBackground = true)
@Composable
fun DailyScreenPreview() {

    DailyScreen()

}