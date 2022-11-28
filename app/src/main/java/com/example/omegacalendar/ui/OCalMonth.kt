package com.example.omegacalendar.ui

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.omegacalendar.data.MonthUiState

@Composable
fun DayComponent(
    day: Int,
    month:Int ,
    year:Int,
    modifier: Modifier = Modifier,
    dayEvents:(mn:Int,day:Int,yr:Int) -> Unit,
    weekNum: Int
){
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
                .align(alignment = Alignment.Start),
            color = if (weekNum == 0 && day in 21..31){//first week of month
                Color.LightGray
            }
            else if (weekNum in 4..5 && day in 1..14){//last two weeks of month
                Color.LightGray
            }
            else {
                Color.Black
            }

        )
        //Spacer(modifier = Modifier.height(4.dp))
        //Text(
        //    text = "month: $month \n year: $year",//"- " + "example text",
//
        //    modifier = Modifier
        //        .fillMaxWidth()
        //        .padding(start = 8.dp, top = 4.dp)
        //        .background(color = Color(238, 130, 238)),
        //    fontSize = 8.sp
        //)
    }
}

@Composable
fun WeekComponent(
    modifier: Modifier = Modifier,
    month: OMonth,
    dayEvents:(mn:Int,day:Int,yr:Int) -> Unit,
    weekNum:Int
){
    val weekList = month.wholeMonth[weekNum]//list of the weekdays
    Row(modifier = modifier){
        DayComponent(weekList[0],month.getMonth(weekNum, weekList[0]) + 1, month.getYear(weekNum, weekList[0]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[1],month.getMonth(weekNum, weekList[1]) + 1, month.getYear(weekNum, weekList[1]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[2],month.getMonth(weekNum, weekList[2]) + 1, month.getYear(weekNum, weekList[2]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[3],month.getMonth(weekNum, weekList[3]) + 1, month.getYear(weekNum, weekList[3]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[4],month.getMonth(weekNum, weekList[4]) + 1, month.getYear(weekNum, weekList[4]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[5],month.getMonth(weekNum, weekList[5]) + 1, month.getYear(weekNum, weekList[5]), Modifier.weight(1f), dayEvents,weekNum)
        DayComponent(weekList[6],month.getMonth(weekNum, weekList[6]) + 1, month.getYear(weekNum, weekList[6]), Modifier.weight(1f), dayEvents,weekNum)
    }
}


@Composable
fun MonthComponent(
    onPrevMonthButtonClicked:() -> Unit,
    onNextMonthButtonClicked:() -> Unit,
    dayEvents:(mn:Int,day:Int,yr:Int) -> Unit,
    m:Int,
    y:Int
){//(monthUiState: MonthUiState) {//(cal: GregorianCalendar){
    //var y by rememberSaveable { mutableStateOf(cal.get(Calendar.YEAR)) }
    //var m by rememberSaveable { mutableStateOf(cal.get(Calendar.MONTH)) }

    val mn = OMonth(m, y)

    Column {
        //top row; button-month-button-year
        Box(modifier = Modifier.fillMaxWidth()){
            //Row(modifier = Modifier.align(Alignment.Center)){
            //
            //}
            Button(
                onClick = { onPrevMonthButtonClicked() },
                modifier = Modifier//.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    .padding(start = 52.dp),
                content = {
                    Text(text = "<", fontSize = 16.sp)
                }
            )
            Text(
                text = mn.monthName,
                modifier = Modifier
                    .wrapContentWidth() //align = Alignment.CenterHorizontally)
                    .padding(horizontal = 12.dp)
                    .align(Alignment.Center),
                fontSize = 32.sp
            )
            Button(
                onClick = { onNextMonthButtonClicked() },
                modifier = Modifier
                    //.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 52.dp)
            ) {
                Text(text = ">", fontSize = 16.sp)
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
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 0, dayEvents = dayEvents)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 1, dayEvents = dayEvents)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 2, dayEvents = dayEvents)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 3, dayEvents = dayEvents)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 4, dayEvents = dayEvents)
        WeekComponent(modifier = Modifier.weight(1f), month = mn, weekNum = 5, dayEvents = dayEvents)
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