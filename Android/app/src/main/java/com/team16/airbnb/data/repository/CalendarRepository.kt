package com.team16.airbnb.data.repository

import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {

    fun getCalendar(): Flow<List<CalendarData>>

    fun setStartDate(startDate: DayInfo): Flow<List<CalendarData>>
}