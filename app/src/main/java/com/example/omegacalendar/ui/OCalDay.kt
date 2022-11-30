package com.example.omegacalendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable     // This is just called upon to get the whole display. Imagine it as the program
fun DailyScreen() {
        DailySlide(
//            EventList = Datasource().loadAffirmations(),
            Y = 0,
            M = 1,
            D = 5,
        )
}


@Composable     // This is where it grabs the things to display. Such as the list along with the buttons
//fun DailySlide(EventList: List<Events>, modifier: Modifier = Modifier, Y: Int, M: Int, D: Int) {
fun DailySlide(modifier: Modifier = Modifier, Y: Int, M: Int, D: Int) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Navigations(Y, M)

        LazyColumn(modifier = Modifier
            .weight(10/12f)
        ) {
//            items(EventList) { Evento ->
//                EventSlide(Evento)
//            }
        }
    }

}

@Composable     // This all focuses on the Top Column, Buttons Year and Month Display
fun Navigations(Y:Int, M:Int) {


    var y = Y
    var m = M
//    var d = D

    val monthname = OMonth(m, y)



    Box(modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.align(Alignment.Center)) {

            Text(
                text = monthname.monthName,     // Month
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

            Text (
                text = monthname.monthName,       // Day
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

            Text(
                text = "Year",      // Year
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 32.sp
            )

        }


    }

}


@Composable         // This all focuses on the Bottom Column, The lazy Column Slide/ Event Displays
//fun EventSlide (Evento: Events, modifier: Modifier = Modifier) {
fun EventSlide (modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Row {
//            if (Evento.DayId == R.string.Tue) {

                Text(   // This is Responsable of displaying the Descritption of the Event
//                    text = LocalContext.current.getString(Evento.EventsId),
                    text ="SAMPLE TEXT",
                    //textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(10 / 16f),
                    style = MaterialTheme.typography.h6
                )
                Text(
//                    text = LocalContext.current.getString(Evento.TimeId),
                    text ="SAMPLE TEXT",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(2 / 16f),
                    style = MaterialTheme.typography.h6
                )
                Text(
//                    text = LocalContext.current.getString(Evento.M),
                    text ="SAMPLE TEXT",
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(2 / 16f),
                    style = MaterialTheme.typography.h6
                )

            }

        }
//    }
}

