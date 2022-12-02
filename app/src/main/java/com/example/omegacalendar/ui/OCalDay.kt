package com.example.omegacalendar.ui

import android.content.Intent
import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.omegacalendar.AddEventActivity
import com.example.omegacalendar.MainActivity
import com.example.omegacalendar.OCalScreen
import com.example.omegacalendar.data.Event
import com.example.omegacalendar.data.EventViewModel
import com.example.omegacalendar.ui.NetworkStuffs.networkrequest
import com.example.omegacalendar.ui.theme.darkGrey
import com.example.omegacalendar.ui.theme.tealBackground
import com.example.omegacalendar.ui.theme.tealSecondary

@Composable
fun DailyScreen(
    day: Int,
    month:Int,
    year:Int,
    modifier: Modifier = Modifier,
    viewModel: EventViewModel,
){
    val events = viewModel.getEventsByDayOrdered(month, day, year).observeAsState(listOf()).value
    val monthName = when (month-1) {
        0 -> "Jan"
        1 -> "Feb"
        2 -> "Mar"
        3 -> "Apr"
        4 -> "May"
        5 -> "Jun"
        6 -> "July"
        7 -> "Aug"
        8 -> "Sept"
        9 -> "Oct"
        10 -> "Nov"
        11 -> "Dec"
        else -> "Invalid month"
    }

    Column(modifier = Modifier.wrapContentWidth()){
        Row(){
            Text(
                text = monthName + " " + day.toString() + ", " + year.toString(),
                modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.h3
            )
        }
        DayEventList(events, viewModel)

    }
}


@Composable
fun DayEventListItem(event: Event, viewModel: EventViewModel){
    val openDialog = remember { mutableStateOf(false) }

    Row() {
        Card() {
            Row(
                modifier = Modifier
                    .clickable(onClick = {
                        openDialog.value=true
                    })
            ) {
                Text(
                    text = event.desc,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(10 / 16f),
                    style = MaterialTheme.typography.h6
                )
                    Text(
                        text = event.startHour.toString() + " - " + event.startMin.toString(),
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .weight(6 / 16f),
                        style = MaterialTheme.typography.h6
                )
            }
        }
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = false
            },
            title = {
                Text(text = "Please select option")
            },
            buttons = {
                Column(
                    modifier = Modifier.padding(all = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.deleteEvent(event)
                            openDialog.value = false
                        }
                    ) {
                        Text("Delete Event")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            networkrequest(event)
                            openDialog.value = false
                        }
                    ) {
                        Text("Discord Announcement")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}

@Composable
fun DayEventList(events: List<Event>, viewModel: EventViewModel){
    LazyColumn() {
        items(events) { event ->
            DayEventListItem(event, viewModel)
            Divider()
        }
    }
}
