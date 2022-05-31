package com.team16.airbnb.data.repository

import com.team16.airbnb.data.datasource.CalendarDataSourceImpl
import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSourceImpl
): CalendarRepository {

    override fun getCalendar() = calendarDataSource.getCalendar()

    override fun setStartDate(startDate: DayInfo) = calendarDataSource.setStartDate(startDate)

}