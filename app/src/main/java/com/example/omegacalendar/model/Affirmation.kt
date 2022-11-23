package com.example.omegacalendar.model

import androidx.annotation.StringRes

// IGNORE THE FACT ITS CALLED AFFIRMATION
// The real gist of this part is that it creates a class that Datasource can call upon.

data class Events(
    @StringRes val EventsId: Int,
    @StringRes val TimeId: Int,
    @StringRes val DayId: Int,
    @StringRes val M: Int,

    )

data class Time(
    @StringRes val stringResourceId: Int,
)

data class Day(
    @StringRes val stringResourceId: Int,
)