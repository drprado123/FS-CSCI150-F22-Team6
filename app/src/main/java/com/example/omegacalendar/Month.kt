package com.example.omegacalendar

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar

class Month (mn:Int, yr:Int) {
    var month = mn
    var year = yr

    val monthName = when (mn) {
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
    val firstDay = 2 - (getFirstDayOfMonth(mn, yr))
    val lastDay = getNumOfDays(mn, yr)
}