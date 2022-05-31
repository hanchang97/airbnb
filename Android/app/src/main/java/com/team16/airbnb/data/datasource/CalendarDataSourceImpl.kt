package com.team16.airbnb.data.datasource

import android.util.Log
import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import java.util.*
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor() : CalendarDataSource {

    private var todayMonth: Int = -1
    private var today: Int = -1


    override fun getCalendar(): Flow<List<CalendarData>> = flow {
        val cal = Calendar.getInstance()
        var month = cal.get(Calendar.MONTH) + 1
        var year = cal.get(Calendar.YEAR)

        todayMonth = month
        today = cal.get(Calendar.DATE)

        val list = mutableListOf<CalendarData>()

        for (i in 0 until 13) {
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
            Log.d("TAG", "${year}년 ${month}월")
            month++
        }
        emit(list)
    }

    override fun setStartDate(startDate: DayInfo): Flow<List<CalendarData>> = flow {
        val cal = Calendar.getInstance()
        var month = cal.get(Calendar.MONTH) + 1
        var year = cal.get(Calendar.YEAR)

        todayMonth = month
        today = cal.get(Calendar.DATE)

        val list = mutableListOf<CalendarData>()

        for (i in 0 until 13) {
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
            Log.d("TAG", "${year}년 ${month}월")
            month++
        }
        emit(list)
    }

    private fun setDays(year: Int, month: Int): List<DayInfo> {
        val cal = Calendar.getInstance()
        cal.set(year, month - 1, 1)
        var start = cal.get(Calendar.DAY_OF_WEEK)
        val days = mutableListOf<DayInfo>()

        for (i in 1 until start) {
            days.add(DayInfo(" ", " ", isPossible = false))
        }

        for (i in 1..cal.getActualMaximum(Calendar.DATE)) {
            when (month == todayMonth) {
                true -> {
                    days.add(
                        DayInfo(
                            day = "$i",
                            month = "$month",
                            isPossible = setIsPossible(today, i)
                        )
                    )
                }

                false -> {
                    days.add(
                        DayInfo(
                            day = "$i",
                            month = "$month",
                            isPossible = true
                        )
                    )
                }
            }

            start++
        }

        return days
    }



    private fun setIsPossible(today: Int, day: Int) = today <= day

}