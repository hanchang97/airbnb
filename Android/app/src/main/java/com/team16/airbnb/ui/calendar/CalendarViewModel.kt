package com.team16.airbnb.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.data.model.CalendarData
import com.team16.airbnb.data.model.DayInfo
import com.team16.airbnb.data.repository.CalendarRepository
import com.team16.airbnb.data.repository.CalendarRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
): ViewModel() {

    private val _calendar = MutableStateFlow<List<CalendarData>>(emptyList())
    val calendar: StateFlow<List<CalendarData>> = _calendar

    private var _startDate = DayInfo("", "")
    val startDate = _startDate

    private var _endDate = DayInfo("", "")
    val endDate = _endDate

    private var todayMonth: Int = -1
    private var today: Int = -1

    init {
        viewModelScope.launch {
            calendarRepository.getCalendar().collect{
                _calendar.value = it
            }
        }
    }

    fun setDate(dayInfo: DayInfo) {
        when {
            _startDate.day == "" && _endDate.day == "" -> {
                _startDate = dayInfo
                setStartDate()
            }

            _startDate.day != "" && _endDate.day == "" -> {
                checkDate(dayInfo)
            }

        }
    }

    private fun checkDate(dayInfo: DayInfo) = when {
        _startDate.month >= dayInfo.month -> {
            checkDay(dayInfo)
        }

        else -> {
            _endDate = dayInfo
        }
    }

    private fun checkDay(dayInfo: DayInfo) = when(_startDate.day >= dayInfo.day) {
         true -> {
            _startDate = DayInfo("", "")
        }
        false -> {
            _endDate = dayInfo
            setStartDate()
        }

    }

    private fun setStartDate() {
        _calendar.value.forEach { data ->
            data.days.forEach {
                if(it.month == _startDate.month && it.day == _startDate.day) {
                    it.isChoice = true
                }
                if(it.month == _endDate.month && it.day == _endDate.day) {
                    it.isChoice = true
                }
            }
        }
    }

    private fun setCancelDate() {
        _calendar.value.forEach { data ->
            data.days.forEach {
                    it.isChoice = false
            }
        }
    }
}
