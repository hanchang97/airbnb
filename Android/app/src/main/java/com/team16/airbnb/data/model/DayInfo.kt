package com.team16.airbnb.data.model

data class DayInfo(
    val day: String,
    val month: String,
    var isPossible: Boolean = false,
    var isChoice: Boolean = false,
    var isChecked: Boolean = false
)
