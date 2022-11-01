package com.example.omegacalendar

import android.os.Bundle
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    val rightNow = GregorianCalendar.getInstance()
                    OmegaCalendarTheme {
                        MonthComponent(rightNow as GregorianCalendar)
                    }
                }
            }
        }
    }
}
class Month(cal: GregorianCalendar){
    val month = cal.get(Calendar.MONTH)
    //val eWeek1 =
}
@Composable
fun DayComponent(day: Int, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxHeight()
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
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp)
                .background(color = Color(238, 130, 238)),
            fontSize = 8.sp
        )
    }
}

@Composable
fun WeekComponent(
    modifier: Modifier = Modifier,
    edgeWeek: Boolean = false,
    startDay: Int = 1,
    lastDay: Int = 28
):Int{
    var sDay = startDay
    Row(modifier = modifier){
        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))

        sDay = if (sDay + 1 > lastDay) 0 else sDay
        DayComponent(day = ++sDay, Modifier.weight(1f))
    }
    return sDay + 1
}

@Composable
fun MonthComponent(cal: GregorianCalendar){//, yearNum: Int = 2022){
    var y by remember { mutableStateOf(cal.get(Calendar.YEAR)) }
    var m by remember { mutableStateOf(cal.get(Calendar.MONTH)) }
    //var d by remember { mutableStateOf(cal.get(Calendar.DAY_OF_MONTH)) }
    //println("ERA: " + rightNow.get(Calendar.ERA))

    var monthName = when (m) {
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
    var firstDay = 2 - (getFirstDayOfMonth(y, m))
    var lastDay = getNumOfDays(cal)

    Column {
        Box(modifier = Modifier.fillMaxWidth()){
            Row(modifier = Modifier.align(Alignment.Center)){

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    //    .align(Alignment.CenterVertically)
                ) {
                    Text(text = "<", fontSize = 16.sp)
                }

                Text(
                    text = monthName,
                    modifier = Modifier
                        .wrapContentWidth() //align = Alignment.CenterHorizontally)
                        .padding(horizontal = 12.dp)
                        ,//.align(Alignment.Center),
                    fontSize = 32.sp
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    //    .align(Alignment.CenterVertically)
                ) {
                    Text(text = ">", fontSize = 16.sp)
                }
            }
            Text(
                text = y.toString(),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }
        Row (modifier = Modifier.padding(start = 8.dp)){
            Text(text = "Sun", modifier = Modifier.weight(1f))
            Text(text = "Mon", modifier = Modifier.weight(1f))
            Text(text = "Tues", modifier = Modifier.weight(1f))
            Text(text = "Wed", modifier = Modifier.weight(1f))
            Text(text = "Thurs", modifier = Modifier.weight(1f))
            Text(text = "Fri", modifier = Modifier.weight(1f).padding(start = 10.dp))
            Text(text = "Sat", modifier = Modifier.weight(1f).padding(start = 10.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        firstDay = WeekComponent(modifier = Modifier.weight(1f), edgeWeek = true, startDay = firstDay)
        firstDay = WeekComponent(modifier = Modifier.weight(1f), startDay = firstDay)
        firstDay = WeekComponent(modifier = Modifier.weight(1f), startDay = firstDay)
        firstDay = WeekComponent(modifier = Modifier.weight(1f), startDay = firstDay, lastDay = lastDay)
        firstDay = WeekComponent(modifier = Modifier.weight(1f), startDay = firstDay, lastDay = lastDay)
        firstDay = WeekComponent(modifier = Modifier.weight(1f), edgeWeek = true, startDay = firstDay, lastDay = lastDay)
        Spacer(modifier = Modifier.height(64.dp))
    }
}

fun getNumOfDays(cal: GregorianCalendar):Int{
    val mn = cal.get(Calendar.MONTH)
    val yr = cal.get(Calendar.YEAR)
    return when(mn){
        0, 2, 4, 6, 7, 9, 11 ->  31
        3, 5, 8, 10 -> 30
        else -> if (cal.isLeapYear(yr)) 29 else 28//(isLeapYear(cal.get(Calendar.YEAR))) 29 else 28
    }
}
fun getFirstDayOfMonth(yr : Int, mn : Int):Int { //day of the week of the
    val cal = GregorianCalendar(yr, mn, 1)  //first day of the month
    return cal.get(Calendar.DAY_OF_WEEK)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val rightNow = GregorianCalendar.getInstance()
    OmegaCalendarTheme {
        MonthComponent(rightNow as GregorianCalendar)
    }
}