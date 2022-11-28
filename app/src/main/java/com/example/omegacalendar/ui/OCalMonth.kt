package com.example.omegacalendar.ui

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.omegacalendar.AddEventActivity
import com.example.omegacalendar.MainActivity
import com.example.omegacalendar.data.Event
import com.example.omegacalendar.data.EventViewModel
import com.example.omegacalendar.data.MonthUiState

@Composable
fun DayComponent(day: Int, month:Int ,year:Int, modifier: Modifier = Modifier, viewModel: EventViewModel){
    val events = viewModel.eventsByDay(month, day, year).observeAsState(listOf()).value

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
            text = "month: $month \n year: $year",//"- " + "example text",

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp)
                .background(color = Color(238, 130, 238)),
            fontSize = 8.sp
        )
        EventList(events, viewModel)

    }
}
@Composable
fun EventListItem(event: Event){
    Row{
        Column{
            Text(text = event.desc)
        }
    }
}
@Composable
fun EventList(events: List<Event>, viewModel: EventViewModel){
    LazyColumn() {
        items(events) { event ->
            EventListItem(event)
            Divider()
        }
    }
}
@Composable
fun WeekComponent(
    modifier: Modifier = Modifier,
    month: OMonth,
    weekNum:Int,
    viewModel: EventViewModel
){
    val weekList = month.wholeMonth[weekNum]//list of the weekdays
    Row(modifier = modifier){
        DayComponent(weekList[0],month.getMonth(weekNum, weekList[0]) + 1, month.getYear(weekNum, weekList[0]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[1],month.getMonth(weekNum, weekList[1]) + 1, month.getYear(weekNum, weekList[1]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[2],month.getMonth(weekNum, weekList[2]) + 1, month.getYear(weekNum, weekList[2]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[3],month.getMonth(weekNum, weekList[3]) + 1, month.getYear(weekNum, weekList[3]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[4],month.getMonth(weekNum, weekList[4]) + 1, month.getYear(weekNum, weekList[4]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[5],month.getMonth(weekNum, weekList[5]) + 1, month.getYear(weekNum, weekList[5]), Modifier.weight(1f), viewModel)
        DayComponent(weekList[6],month.getMonth(weekNum, weekList[6]) + 1, month.getYear(weekNum, weekList[6]), Modifier.weight(1f), viewModel)
    }
}


@Composable
fun MonthComponent(
    onPrevMonthButtonClicked:() -> Unit,
    onNextMonthButtonClicked:() -> Unit,
    viewModel: EventViewModel,
    m:Int,
    y:Int
){//(monthUiState: MonthUiState) {//(cal: GregorianCalendar){
    //var y by rememberSaveable { mutableStateOf(cal.get(Calendar.YEAR)) }
    //var m by rememberSaveable { mutableStateOf(cal.get(Calendar.MONTH)) }

    //val events = viewModel.events.observeAsState(listOf()).value
    val mn = OMonth(m, y)

    Column {
        //top row; button-month-button-year
        Box(modifier = Modifier.fillMaxWidth()){
            Button(
                onClick = {
                    MainActivity.applicationContext().startActivity(Intent(MainActivity.applicationContext(), AddEventActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK))
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                    .padding(end = 12.dp)
            ) {Text(text = "Add", fontSize = 12.sp)}
            Row(modifier = Modifier.align(Alignment.Center)){
                Button(
                    onClick = { onPrevMonthButtonClicked() },
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    //    .align(Alignment.CenterVertically)
                ) {
                    Text(text = "<", fontSize = 16.sp)
                }

                Text(
                    text = mn.monthName,
                    modifier = Modifier
                        .wrapContentWidth() //align = Alignment.CenterHorizontally)
                        .padding(horizontal = 12.dp)
                    ,//.align(Alignment.Center),
                    fontSize = 32.sp
                )
                Button(
                    onClick = { onNextMonthButtonClicked() },
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
        //second row; names of the months
        Row (modifier = Modifier.padding(start = 8.dp)){
            Text(text = "Sun", modifier = Modifier.weight(1f))
            Text(text = "Mon", modifier = Modifier.weight(1f))
            Text(text = "Tues", modifier = Modifier.weight(1f))
            Text(text = "Wed", modifier = Modifier.weight(1f))
            Text(text = "Thurs", modifier = Modifier.weight(1f))
            Text(text = "Fri", modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp))
            Text(text = "Sat", modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))

        //column of weeks
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 0, viewModel)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 1, viewModel)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 2, viewModel)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 3, viewModel)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 4, viewModel)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 5, viewModel)
        Spacer(modifier = Modifier.height(64.dp))
    }
}

fun getNumOfDays(mn:Int, yr:Int):Int{

    return when(mn){
        0, 2, 4, 6, 7, 9, 11 ->  31
        3, 5, 8, 10 -> 30
        else -> if (yr % 4 == 0 && yr % 100 != 0 || yr % 400 == 0 ) 29 else 28
    }
}
fun getFirstDayOfMonth(mn : Int, yr : Int):Int { //day of the week of the
    val cal = GregorianCalendar(yr, mn, 1)  //first day of the month
    return cal.get(Calendar.DAY_OF_WEEK)

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val rightNow = GregorianCalendar(2022,11,5)//.getInstance()
//
//    OmegaCalendarTheme {
//        MonthComponent(rightNow)
//    }
//}