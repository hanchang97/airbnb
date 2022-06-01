package com.team16.airbnb.ui.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.SimpleItemAnimator
import com.team16.airbnb.R
import com.team16.airbnb.common.DateChoiceListener
import com.team16.airbnb.data.model.DayInfo
import com.team16.airbnb.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalendarFragment : Fragment(), DateChoiceListener {

    private val binding: FragmentCalendarBinding by lazy {
        FragmentCalendarBinding.inflate(layoutInflater)
    }

    private val calendarViewModel: CalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarAdapter = CalendarAdapter(this)

        binding.rvCalendar.apply {
            adapter = calendarAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.calendar.collect {
                    calendarAdapter.submitList(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.label.collect {
                    binding.label = it
                }
            }
        }

    }

    override fun setDate(dayInfo: DayInfo) {
        calendarViewModel.setDate(dayInfo)
    }

}