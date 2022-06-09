package com.team16.airbnb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.common.ApiState
import com.team16.airbnb.data.dto.near.NearResult
import com.team16.airbnb.data.model.NearInfo
import com.team16.airbnb.data.repository.HomeRepository
import com.team16.airbnb.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository, private val searchRepository: SearchRepository) : ViewModel() {

    private val _heroInfo = MutableStateFlow("")
    val heroInfo = _heroInfo

    private val _nearTripList = MutableStateFlow<List<NearInfo>>(emptyList())
    val nearTripList = _nearTripList

    private val _recommendTheme = MutableStateFlow<List<NearInfo>>(emptyList())
    val recommendTheme = _recommendTheme

    private val _nearListStateFlow = MutableStateFlow<ApiState<List<NearResult>>>(ApiState.Empty)
    val nearListStaeFlow: StateFlow<ApiState<List<NearResult>>> =_nearListStateFlow

    private val _searchAreaListStateFlow = MutableStateFlow<ApiState<List<String>>>(ApiState.Empty)
    val searchAreaListStaeFlow: StateFlow<ApiState<List<String>>> =_searchAreaListStateFlow

    init {
        getNearTripList()
        getRecommendTheme()
    }

    private fun getHeroInfo() {
        _heroInfo.value
    }

    private fun getNearTripList() {
        viewModelScope.launch {
            repository.getNearInfo().collect{
                _nearTripList.value = it
            }
        }
    }

    private fun getRecommendTheme(){
        viewModelScope.launch {
            repository.getRecommendThem().collect{
                _recommendTheme.value = it
            }
        }
    }

    fun getNearList(){
        viewModelScope.launch {
            _nearListStateFlow.value = ApiState.Loading
            repository.getNearList()
                .catch { e ->
                    _nearListStateFlow.value = ApiState.Error(e.message)
                }.collect{ data ->
                    data.nearResult?.let {
                        _nearListStateFlow.value = ApiState.Success(it)
                    }
                }
        }
    }

    // 검색어로 여행지 검색
    fun getSearchAreaList(address: String){
        viewModelScope.launch {
            _searchAreaListStateFlow.value = ApiState.Loading
            searchRepository.getSearchAreaList(address)
                .catch { e ->
                    _searchAreaListStateFlow.value = ApiState.Error(e.message)
                }.collect{ data ->
                    data.result?.let {
                        _searchAreaListStateFlow.value = ApiState.Success(it)
                    }
                }
        }
    }
}