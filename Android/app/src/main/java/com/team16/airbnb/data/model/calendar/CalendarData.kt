package com.team16.airbnb.data.model.calendar

import com.team16.airbnb.data.model.DayInfo

data class CalendarData(
    val header: String,
    val days: Map<Int, DayInfo>
)