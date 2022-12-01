package com.example.omegacalendar.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.omegacalendar.OCalScreen
import com.example.omegacalendar.data.Event
import com.example.omegacalendar.data.EventViewModel

@Composable
fun DailyScreen(
    day: Int,
    month:Int,
    year:Int,
    modifier: Modifier = Modifier,
    viewModel: EventViewModel,
){
    val events = viewModel.eventsByDay(month, day, year).observeAsState(listOf()).value

    Column(modifier = Modifier.wrapContentWidth()){
        Text(
            text = month.toString() + " " + day.toString() + " " + year.toString(),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(4 / 16f),
            style = MaterialTheme.typography.h6
        )
        DayEventList(events)
    }
}

@Composable
fun DayEventListItem(event: Event){
    Row() {
        Card() {
            Row() {
                Text(
                    text = event.desc,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(10 / 16f),
                    style = MaterialTheme.typography.h6
                )

//                Text(
//                    text = event.startMin.toString(),
//                    modifier = Modifier,
////                        .weight(2 / 16f),
//                    style = MaterialTheme.typography.h6
//                )
//
//                Text(
//                    text = "-",
//                    modifier = Modifier,
////                        .weight(1/16f),
//                    style = MaterialTheme.typography.h6
//                )
//
//                Text(
//                    text = event.endMin.toString(),
//                    modifier = Modifier,
////                        .weight(2 / 16f),
//                    style = MaterialTheme.typography.h6
//                )

            }
        }
    }
}

@Composable
fun DayEventList(events: List<Event>){
    LazyColumn() {
        items(events) { event ->
            DayEventListItem(event)
            Divider()
        }
    }
}

