package com.team16.airbnb.ui.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import com.team16.airbnb.data.repository.CalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {

    private val _calendar = MutableStateFlow<List<CalendarData>>(emptyList())
    val calendar: StateFlow<List<CalendarData>> = _calendar

    private var startDate = DayInfo("", "")

    private var endDate = DayInfo("", "")

    init {
        viewModelScope.launch {
            calendarRepository.getCalendar().collect {
                _calendar.value = it
            }
        }
    }

    fun setDate(dayInfo: DayInfo) {
        when {
            startDate.day == "" && endDate.day == "" -> {
                Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  _startDate.day == \"\" && _endDate.day == \"\"")

                startDate = dayInfo
                setPickDate()
            }

            startDate.day != "" && endDate.day == "" -> {
                Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  _startDate.day != \"\" && _endDate.day == \"\"")

                checkDate(dayInfo)
            }

            startDate.day != "" && endDate.day != "" -> {
                Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  _startDate.day != \"\" && _endDate.day != \"\"")

                setCancelDate()
            }

        }
    }

    private fun checkDate(dayInfo: DayInfo) = when {
        startDate.month >= dayInfo.month -> {
            Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  checkDate")

            checkDay(dayInfo)
        }

        else -> {
            Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  checkDate  else")
            dayInfo.isEnd = true
            endDate = dayInfo
            setPickDate()
        }
    }

    private fun checkDay(dayInfo: DayInfo) = when (startDate.day.toInt() >= dayInfo.day.toInt()) {

        true -> {
            Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  checkDay  true")
            startDate = DayInfo("", "")
            setCancelDate()
        }

        false -> {
            Log.d("TAG", " _startDate = ${startDate.month} ${startDate.day} dayInfo = ${dayInfo.month} ${dayInfo.day}  checkDay  false")

            endDate = dayInfo
            setPickDate()
        }

    }

    private fun setPickDate() {

        val list = getCopyList()

        list.forEach { data ->
            data.days.forEach {
                if (it.month == startDate.month && it.day == startDate.day ||
                    it.month == endDate.month && it.day == endDate.day
                ) {
                    it.isChoice = true
                }
            }
        }
        _calendar.value = list
    }



    private fun setCancelDate() {
        val list = getCopyList()
        list.forEach { data ->
            data.days.forEach {
                it.isChoice = false
            }
        }
        startDate = DayInfo("", "")
        endDate = DayInfo("", "")
        _calendar.value = list
    }

    private fun getCopyList(): List<CalendarData> {
        val list = mutableListOf<CalendarData>()
        _calendar.value.forEach { data ->
            val days = mutableListOf<DayInfo>()
            data.days.forEach {
                days.add(it.copy())
            }
            list.add(CalendarData(data.header, days))
        }
        return list
    }
}
