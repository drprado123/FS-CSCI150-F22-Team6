package com.example.omegacalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmegaCalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MonthComponent()
                }
            }
        }
    }
}

@Composable
fun DayComponent(day: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .clickable(onClick = {})
        .padding(2.dp)
        .border(
            width = 2.dp,
            color = Color.LightGray,
            shape = RoundedCornerShape(4.dp)
        )

    ){
        Text(
            text = day.toString(),//date number
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                //.fillMaxWidth()
                .align(alignment = Alignment.Start)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "- " + "example text",
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 4.dp),
            fontSize = 8.sp
        )
    }
}

@Composable
fun WeekComponent(
    modifier: Modifier = Modifier,
    edgeWeek: Boolean = false,
    startDay: Int = 1,
    endDay: Int = 7
){
    Row(modifier = modifier){
        DayComponent(day = 1, Modifier.weight(1f))
        DayComponent(day = 2, Modifier.weight(1f))
        DayComponent(day = 3, Modifier.weight(1f))
        DayComponent(day = 4, Modifier.weight(1f))
        DayComponent(day = 5, Modifier.weight(1f))
        DayComponent(day = 6, Modifier.weight(1f))
        DayComponent(day = 7, Modifier.weight(1f))
    }
}

@Composable
fun MonthComponent(monthNum: Int = 1){
    var monthName = when(monthNum){
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid month"
    }
    Column {
        Text(
            text = monthName,
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
        )
        WeekComponent(Modifier.weight(1f))
        WeekComponent(Modifier.weight(1f))
        WeekComponent(Modifier.weight(1f))
        WeekComponent(Modifier.weight(1f))
        WeekComponent(Modifier.weight(1f))
        WeekComponent(Modifier.weight(1f))
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OmegaCalendarTheme {
        MonthComponent()
    }
}