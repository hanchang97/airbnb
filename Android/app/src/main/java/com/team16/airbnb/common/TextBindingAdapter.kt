package com.team16.airbnb.common

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.team16.airbnb.R
import com.team16.airbnb.data.model.DayInfo

@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("dayCheck")
fun isBeforeDay(dayView: TextView, item: DayInfo) {
    dayView.text = item.day
    Log.e("TAG", "item.isPossible ${item.isPossible} day = ${item.day}")
    if(!item.isPossible) {
        dayView.setTextColor(Color.parseColor("#828282") )
        dayView.isEnabled = false
    }
    if(item.isChoice) {
        dayView.apply {
            setTextColor(Color.parseColor("#FFFFFF"))
            background = dayView.context.getDrawable(R.drawable.ic_choice_date)
        }

    }
}


