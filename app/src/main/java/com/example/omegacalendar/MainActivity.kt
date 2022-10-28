package com.example.omegacalendar

import android.os.Bundle
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
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
class GCalendar{
    val calendar = Calendar.getInstance()
}
@Composable
fun DayComponent(day: Int, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
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
fun MonthComponent(monthNum: Int = 1, yearNum: Int = 2022){
    val rightNow = GregorianCalendar.getInstance()
    //println("ERA: " + rightNow.get(Calendar.ERA))

    var monthName = when(rightNow.get(Calendar.MONTH)){
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> "Invalid month"
    }

    Column {
        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                text = monthName,
                modifier = Modifier
                    .wrapContentWidth() //align = Alignment.CenterHorizontally)
                    .align(Alignment.Center),
                fontSize = 32.sp
            )
            Text(
                text = yearNum.toString(),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
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