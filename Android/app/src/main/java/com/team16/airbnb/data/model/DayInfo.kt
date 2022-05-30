package com.team16.airbnb.data.model

data class DayInfo(
    val day: String,
    val isPossible: Boolean = false,
    val isChoice: Boolean = false,
    val isChecked: Boolean = false
)
