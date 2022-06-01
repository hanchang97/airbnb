package com.team16.airbnb.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.common.DateChoiceListener
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

    private val _label = MutableStateFlow("")
    val label: StateFlow<String> = _label

    private var startDate = DayInfo(day= "", month = "")

    private var endDate = DayInfo(day = "", month = "")

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
                startDate = dayInfo
                setPickDate()
                setStartDateLabel(dayInfo)
            }

            startDate.day != "" && endDate.day == "" -> {
                checkDate(dayInfo)
            }

            startDate.day != "" && endDate.day != "" -> {
                setCancelDate()
            }

        }

    }

    private fun checkDate(dayInfo: DayInfo) = when {

        startDate.month >= dayInfo.month -> {
            checkDay(dayInfo)
        }

        else -> {
            endDate = dayInfo
            setPickDate()
            setEndDateLabel(dayInfo)
        }
    }

    private fun checkDay(dayInfo: DayInfo) = when (startDate.day.toInt() >= dayInfo.day.toInt()) {

        true -> {
            startDate = DayInfo(day= "", month = "")
            setCancelDate()
        }

        false -> {
            endDate = dayInfo
            setPickDate()
            setEndDateLabel(dayInfo)
        }

    }

    private fun setPickDate() {

        val list = getCopyList()

        list.forEach { data ->

            data.days.forEach {
                when {

//                    it.month == startDate.month && it.day == startDate.day -> {
//                        it.isStart = true
//                        it.isChoice = true
//                        it.isChecked = true
//                    }

                    it.id == startDate.id -> {
                        it.isStart = true
                        it.isChoice = true
                        it.isChecked = true
                    }

//                    it.month == endDate.month && it.day == endDate.day -> {
//                        it.isChecked = true
//                        it.isEnd = true
//                        it.isChoice = true
//                    }

                    it.id == endDate.id -> {
                        it.isEnd = true
                        it.isChoice = true
                        it.isChecked = true
                    }

//                    startDate.month == it.month && startDate.day.dayToInt() <= it.day.dayToInt() && endDate.day.dayToInt() >= it.day.dayToInt() -> {
//                        it.isChecked = true
//                    }

                    startDate.id <= it.id && endDate.id >= it.id -> {
                        it.isChecked = true
                    }

                }
            }
        }
        _calendar.value = list
    }

//    private fun String.dayToInt() =
//        when (this != "") {
//            true -> this.toInt()
//            false -> -1
//        }


    private fun setCancelDate() {
        val list = getCopyList()
        list.forEach { data ->
            data.days.forEach {
                it.apply {
                    isChoice = false
                    isStart = false
                    isEnd = false
                    isChecked = false
                }
            }
        }
        startDate = DayInfo(day= "", month = "")
        endDate = DayInfo(day= "", month = "")

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

    private fun setStartDateLabel(startDate: DayInfo) {
        _label.value = "${startDate.month}월 ${startDate.day}일"
    }

    private fun setEndDateLabel(endDate: DayInfo) {
        _label.value = "${startDate.month}월 ${startDate.day}일 - ${endDate.month}월 ${endDate.day}일"
    }

}
