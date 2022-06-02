package com.team16.airbnb.data.datasource

import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import kotlinx.coroutines.flow.Flow

interface CalendarDataSource {
    fun getCalendar(): Flow<List<CalendarData>>

    fun setPickDate(startDate: DayInfo, endDate: DayInfo): Flow<List<CalendarData>>
}