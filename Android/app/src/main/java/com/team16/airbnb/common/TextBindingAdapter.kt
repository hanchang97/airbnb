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

    when {
        !item.isPossible -> {
            dayView.setTextColor(Color.parseColor("#828282") )
        }

        item.isChoice && item.isPossible && !item.isChecked -> {
            dayView.apply {
                setTextColor(Color.parseColor("#FFFFFF"))
                background = dayView.context.getDrawable(R.drawable.ic_choice_date)
            }
        }
        // end까지 선택됨
        item.isChoice && item.isPossible && item.isChecked -> {
            when {

                item.isStart -> {
                    dayView.apply {
                        setTextColor(Color.parseColor("#FFFFFF"))
                        background = dayView.context.getDrawable(R.drawable.ic_date_complete_start)
                    }
                }

                item.isEnd -> {
                    dayView.apply {
                        setTextColor(Color.parseColor("#FFFFFF"))
                        background = dayView.context.getDrawable(R.drawable.ic_date_complete_end)
                    }
                }

            }

        }
        // start와 end 사이 날짜
        !item.isChoice && item.isPossible && item.isChecked -> {
            dayView.apply {
                background = dayView.context.getDrawable(R.drawable.ic_date_range)
            }
        }
        item.isPossible && !item.isChoice -> {
            dayView.apply {
                setTextColor(Color.parseColor("#000000"))
                background = null
            }
        }

    }

}


