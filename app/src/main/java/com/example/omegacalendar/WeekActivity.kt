package com.example.calendarweekview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.calendarweekview.ui.theme.CalendarWeekViewTheme

val padUnit = 25

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ViewContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){     //THIS IS A CONTAINER FUNCTION
    CalendarWeekViewTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun ViewContent(names: List<String> = List(1000){"Hello Android $it"}){
    val hours: List<String> = List(24){"$it:00"}
    val days: List<String> = listOf("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")

    // HEADER
    LazyRow(modifier = Modifier.height(40.dp).zIndex(1f)) {
        items(items = days) {
            Text(text = it, modifier = Modifier.padding(padUnit.dp))
        }
    }
    Surface(color = Color(241, 249, 255)) {
        Row(modifier = Modifier.fillMaxHeight()) {
            // HOURS COLUMN
            Surface(color = Color(188, 224, 253)) {
                LazyColumn(modifier = Modifier.width(75.dp)) {
                    items(items = hours) {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(5.dp, padUnit.dp, padUnit.dp, padUnit.dp)
                        )
                    }
                }
            }
            DayData(names = names, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DayData(names: List<String>, modifier: Modifier=Modifier){

    // MODIFIER COLUMN IS FLEXIBLE AND WILL EXPAND TO TAKE AS MUCH SPACE AS IT WANTS
    LazyColumn(modifier = modifier) {
        items(items = names){
            EventDisplay(name = it)
            Divider()
        }

    }
}

@Composable
fun EventDisplay(name: String) {
    var isSelected by remember{
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if(isSelected) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 4000)
    )
    Surface(color = targetColor) {
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .clickable { isSelected = !isSelected }
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        ViewContent()
    }
}
