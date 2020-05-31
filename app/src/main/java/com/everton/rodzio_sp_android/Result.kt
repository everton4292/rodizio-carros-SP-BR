package com.everton.rodzio_sp_android

import android.icu.util.Calendar
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

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