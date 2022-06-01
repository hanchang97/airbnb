package com.team16.airbnb.data.datasource

import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor() : CalendarDataSource {

    private var todayMonth: Int = -1
    private var today: Int = -1
    private var id: Int = 0

    override fun getCalendar(): Flow<List<CalendarData>> = flow {
        val cal = Calendar.getInstance()
        var month = cal.get(Calendar.MONTH) + 1
        var year = cal.get(Calendar.YEAR)

        todayMonth = month
        today = cal.get(Calendar.DATE)

        val list = mutableListOf<CalendarData>()

        for (i in 0 until 12) {
            if (month > 12) {
                month = 1
                year++
            }

            list.add(
                CalendarData(
                    "${year}년 ${month}월",
                    setDays(year, month)
                )
            )
            month++
        }
        emit(list)
    }

    override fun setPickDate(startDate: DayInfo, endDate: DayInfo): Flow<List<CalendarData>> =
        flow {
            val cal = Calendar.getInstance()
            var month = cal.get(Calendar.MONTH) + 1
            var year = cal.get(Calendar.YEAR)

            todayMonth = month
            today = cal.get(Calendar.DATE)

            val list = mutableListOf<CalendarData>()

            for (i in 0 until 12) {
                if (month > 12) {
                    month = 1
                    year++
                }

                list.add(
                    CalendarData(
                        "${year}년 ${month}월",
                        setPickDay(year, month, startDate, endDate)
                    )
                )

                month++
            }
            emit(list)
        }

    private fun setDays(year: Int, month: Int): Map<Int, DayInfo> {
        val cal = Calendar.getInstance()
        cal.set(year, month - 1, 1)
        var start = cal.get(Calendar.DAY_OF_WEEK)
        val days = mutableMapOf<Int, DayInfo>()

        for (i in 1 until start) {
            days[id] = DayInfo(id," ", " ", isPossible = false)
            id++
        }

        for (i in 1..cal.getActualMaximum(Calendar.DATE)) {
            when (month == todayMonth) {
                true -> {
                    days[id] = DayInfo(
                        id = id,
                        day = "$i",
                        month = "$month",
                        isPossible = setIsPossible(today, i)
                    )
                    id++
                }

                false -> {
                    days[id] =
                        DayInfo(
                            id = id,
                            day = "$i",
                            month = "$month",
                            isPossible = true
                        )
                    id++
                }
            }

            start++
        }

        return days
    }

    private fun setPickDay(
        year: Int,
        month: Int,
        startDate: DayInfo,
        endDate: DayInfo
    ): Map<Int, DayInfo> {
        val cal = Calendar.getInstance()
        cal.set(year, month - 1, 1)
        var start = cal.get(Calendar.DAY_OF_WEEK)
        val days = mutableMapOf<Int, DayInfo>()

        for (i in 1 until start) {
            days[id] = DayInfo(day= "", month = "", isPossible = false)
        }

        for (i in 1..cal.getActualMaximum(Calendar.DATE)) {
            when (month == todayMonth) {
                true -> {
                    days[id] =
                        DayInfo(
                            id = id,
                            day = "$i",
                            month = "$month",
                            isPossible = setIsPossible(today, i),
                            isChoice = setChoice(
                                "$month",
                                startDate.month,
                                endDate.month,
                                "$i",
                                startDate.day,
                                endDate.day
                            )
                        )
                    id++
                }

                false -> {
                    days[id] =
                        DayInfo(
                            id = id,
                            day = "$i",
                            month = "$month",
                            isPossible = true,
                            isChoice = setChoice(
                                "$month",
                                startDate.month,
                                endDate.month,
                                "$i",
                                startDate.day,
                                endDate.day
                            )
                        )

                    id++
                }
            }

            start++
        }

        return days
    }

    private fun setChoice(
        month: String,
        startMonth: String,
        endMonth: String,
        day: String,
        startDay: String,
        endDay: String
    ) =
        month == startMonth && day == startDay || month == endMonth && day == endDay


    private fun setIsPossible(today: Int, day: Int) = today <= day

}