package com.everton.rodzio_sp_android

import android.icu.util.Calendar
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

/**
 * Data class for the days of the week and the message indicating if the car is on the car rotation system or not.
 * The days of the week are set up using the DayOfWeek class, getting their FULL display names and getting the default
 * location of the Android SDK, converting it to a String for comparison of the car rotation system main function
 * "verificaRod". The "mensagem" variable is used to show the final message indicating the result "Seu carro está no rodízio",
 * which is a affirmative for the rotation system, "Seu carro não está no rodízio" and "Não há rodízio nos finais de semana", which
 * are a negative for the rotation system.
 *
 */

data class Result (
    var sunday: String = DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var monday: String = DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var tuesday: String = DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var wednesday: String = DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var thursday: String = DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var friday: String = DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var saturday: String = DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString(),
    var mensagem: String = ""

)