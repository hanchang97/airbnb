package com.team16.airbnb.ui.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*


class CalendarViewModel: ViewModel() {

    private val _calendar = MutableStateFlow<List<CalendarData>>(emptyList())
    val calendar: StateFlow<List<CalendarData>> = _calendar

    private var todayMonth: Int = -1
    private var today: Int = -1

    init {
        setCalendar()
    }

    private fun setCalendar() {
        val cal = Calendar.getInstance()
        var month = cal.get(Calendar.MONTH) + 1
        var year = cal.get(Calendar.YEAR)

        todayMonth = month
        today = cal.get(Calendar.DATE)

        val list = mutableListOf<CalendarData>()

        for (i in 0 until 13) {
            if(month > 12) {
                month = 1
                year++
            }

            list.add(
                CalendarData(
                    "${year}년 ${month}월",
                    calendar(year, month)
                )
            )
            month++
        }
    }

    private fun calendar(year: Int, month: Int): List<DayInfo> {
        val cal = Calendar.getInstance()
        cal.set(year, month - 1, 1)
        var start = cal.get(Calendar.DAY_OF_WEEK)
        val days = mutableListOf<DayInfo>()

        for (i in 1 until start) {
            days.add(DayInfo(" ", isPossible = false))
            Log.d("Tag", "${days[i].day}")
        }


        for (i in 1..cal.getActualMaximum(Calendar.DATE)) {
            when(month == todayMonth) {
                true -> {
                    days.add(
                        DayInfo(
                            day = "$i",
                            isPossible = setIsPossible(today, i)
                        )
                    )
                }

                false -> {
                    days.add(
                        DayInfo(
                            day = "$i",
                            isPossible = true
                        )
                    )
                }
            }

            start++
        }

        return days
    }

    private fun setIsPossible(today: Int, day: Int) = today > day

}
