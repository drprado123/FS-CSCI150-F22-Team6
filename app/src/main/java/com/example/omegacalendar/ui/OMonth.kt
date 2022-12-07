package com.example.omegacalendar.ui

class OMonth (mn:Int, yr:Int) {
    var month = mn
    var year = yr

    //get name of the month
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
    // first day of month; number <= 1 representing how many
    // days before day 1 in first week. ex [-2,-1,0,1,2,3,4]

    val firstDay = 2 - (getFirstDayOfMonth(month, year))
    //val lastDay = getNumOfDays(month, year)

    // number of next month
    val nextMonth = getNextMonth()

    // number of previous month
    val prevMonth = getPrevMonth()

    val w1 = getWeek(startDay = firstDay)
    val w2 = getWeek(startDay = w1[6]+1)
    val w3 = getWeek(startDay = w2[6]+1)
    val w4 = getWeek(startDay = w3[6]+1)
    val w5 = getWeek(startDay = w4[6]+1)
    val w6 = getWeek(startDay = w5[6]+1)
    val wholeMonth = listOf(w1, w2, w3, w4, w5, w6)// whole month matrix; wholeMonth[week#][day#]


    private fun getNextMonth(m:Int = month):Int{
        // if next month is january
        return when (m+1){
            12 -> 0
            else -> m+1
        }
    }
    private fun getPrevMonth(m:Int = month):Int{
        // if prev month is december
        return when (m-1){
            -1 -> 11
            else -> m-1
        }
    }
    fun getYear(weekNum:Int = 0, dayNum:Int = 0):Int{//returns what year the day is in
        return if (weekNum == 0 && dayNum in 21..31){//first week of month
            when (prevMonth == 11){// previous month December
                true -> year - 1
                else -> year
            }
        }
        else if (weekNum in 4..5 && dayNum in 1..14){//last two weeks of month
            when (nextMonth == 0){//next month January
                true -> year + 1
                else -> year
            }
        }
        else {
            year
        }

    }
    fun getMonth(weekNum:Int = 0, dayNum:Int = 0):Int{//returns what year the day is in
        return if (weekNum == 0 && dayNum in 21..31){//first week of month
            prevMonth
        }
        else if (weekNum in 4..5 && dayNum in 1..14){//last two weeks of month
            nextMonth
        }
        else {
            month
        }
    }
    private fun getWeek( //returns list of days of the week
        startDay:Int = getFirstDayOfMonth(month, year),
        lastDay:Int = getNumOfDays(month, year)//lastDayOfMonth
    ):List<Int>{
        var sDay = startDay
        val week = mutableListOf<Int> ()
        var i = 0

        while(i < 7){

            //determine what day should be added to list of week
            sDay = when (i){
                0 -> getDay(sDay, lastDay)
                else -> getDay(sDay + 1, lastDay)
            }
                                                                     //   -1   0
            // when sDay is a negative number or 0; day of prev month ex.[30, 31, 1, 2, 3, 4, 5]
            when (sDay < 1){
                true -> week.add(getNumOfDays(prevMonth, year) + sDay)
                else -> week.add(sDay)
            }
            ++i
        }
        return week
    }

    //day is either start of the next month (1) or current month (day)
    private fun getDay(day:Int = 0, lastDayOfMonth:Int = 28):Int{
        return when (day > lastDayOfMonth){
            true -> 1
            else -> day
        }
    }
}