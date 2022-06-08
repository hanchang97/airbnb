package com.team16.airbnb.ui.home

import androidx.lifecycle.ViewModel
import com.team16.airbnb.data.model.NearInfo
import com.team16.airbnb.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    private val _heroInfo = MutableStateFlow("")
    val heroInfo = _heroInfo

    private val _nearTripList = MutableStateFlow<List<NearInfo>>(emptyList())
    val nearTripList = _nearTripList

    private val _recommendTheme = MutableStateFlow<List<NearInfo>>(emptyList())
    val recommendTheme = _recommendTheme

    init {

    }

    private fun getHeroInfo() {
        _heroInfo.value
    }

    private fun getNearTripList() = repository.getNearInfo()

    private fun getRecommendTheme() = repository.getRecommendThem()
}