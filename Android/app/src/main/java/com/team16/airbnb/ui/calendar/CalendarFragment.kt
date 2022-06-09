package com.team16.airbnb.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.team16.airbnb.common.DateChoiceListener
import com.team16.airbnb.data.model.DayInfo
import com.team16.airbnb.databinding.FragmentCalendarBinding
import com.team16.airbnb.ui.search.detail.DetailSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalendarFragment : Fragment(), DateChoiceListener {

    private lateinit var binding: FragmentCalendarBinding

    private val calendarViewModel: DetailSearchViewModel by activityViewModels()

    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarAdapter = CalendarAdapter(this)

        binding.rvCalendar.apply {
            adapter = calendarAdapter
        }
        setCalendar()
        setCalendarLabel()

    }

    override fun setDate(dayInfo: DayInfo) {
        calendarViewModel.setDate(dayInfo)
    }

    private fun setCalendar() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.calendar.collect {
                    calendarAdapter.submitList(it)
                }
            }
        }
    }

    private fun setCalendarLabel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.label.collect {
                    binding.label = it
                }
            }
        }
    }

}