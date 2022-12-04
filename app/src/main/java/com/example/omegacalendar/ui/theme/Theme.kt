package com.example.omegacalendar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = dTealPrimary,
    primaryVariant = dTealAlt,
    secondary = dTealSecondary,
    background = dTealBackground,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = tealPrimary,
    primaryVariant = tealAlt,
    secondary = tealSecondary,
    background = tealBackground,
    onPrimary = darkGrey


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

var alertBackgroundColor = Color.White
var nonCurrentMonthDayColor = Color.LightGray
var currentMonthDayColor = Color.Black
var monthEventTextColor = Color.DarkGray
var monthEventColor = Color(255,204,203)

@Composable
fun OmegaCalendarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (darkTheme){
        alertBackgroundColor = Color.DarkGray
        nonCurrentMonthDayColor = Color(100,100,100)
        currentMonthDayColor = Color.White
        monthEventTextColor = Color.Black
        monthEventColor = Color(200,50,0)

    } else{
        alertBackgroundColor = Color.White
        nonCurrentMonthDayColor = Color.LightGray
        currentMonthDayColor = Color.Black
        monthEventTextColor = Color.DarkGray
        monthEventColor = Color(255, 182, 182)
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}