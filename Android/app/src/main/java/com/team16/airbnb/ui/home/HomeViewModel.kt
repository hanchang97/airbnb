package com.team16.airbnb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.common.ApiState
import com.team16.airbnb.data.model.home.NearResultResponse
import com.team16.airbnb.data.repository.HomeRepository
import com.team16.airbnb.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository, private val searchRepository: SearchRepository) : ViewModel() {

    private val _heroInfo = MutableStateFlow<List<NearResultResponse.NearResult>>(emptyList())
    val heroInfo = _heroInfo

    private val _nearTripList = MutableStateFlow<List<NearResultResponse.NearResult>>(emptyList())
    val nearTripList = _nearTripList

    private val _recommendTheme = MutableStateFlow<List<NearResultResponse.NearResult>>(emptyList())
    val recommendTheme = _recommendTheme

    private val _nearListStateFlow =
        MutableStateFlow<ApiState<List<NearResultResponse.NearResult>>>(ApiState.Empty)
    val nearListStateFlow: StateFlow<ApiState<List<NearResultResponse.NearResult>>> =
        _nearListStateFlow

    private val _searchList = MutableStateFlow<ApiState<List<String>>>(ApiState.Empty)
    val searchList: StateFlow<ApiState<List<String>>> = _searchList

    private val _searchAreaListStateFlow = MutableStateFlow<ApiState<List<String>>>(ApiState.Empty)
    val searchAreaListStaeFlow: StateFlow<ApiState<List<String>>> =_searchAreaListStateFlow

    init {
        getNearList()
        getRecommendTheme()
        getHeroInfo()
    }

    private fun getHeroInfo() {
        viewModelScope.launch {
            repository.getHeroInfo().collect {
                _heroInfo.value = it.nearResult
            }
        }
    }

//    private fun getNearTripList() {
//        viewModelScope.launch {
//            repository.getNearInfo().collect{
//                _nearTripList.value = it
//            }
//        }
//    }

    private fun getRecommendTheme() {
        viewModelScope.launch {
            repository.getRecommendTheme().collect {
                _recommendTheme.value = it.nearResult
            }
        }
    }

    fun getNearList() {
        viewModelScope.launch {
            _nearListStateFlow.value = ApiState.Loading
            repository.getNearList()
                .catch { e ->
                    _nearListStateFlow.value = ApiState.Error(e.message)
                }.collect { data ->
                    _nearListStateFlow.value = ApiState.Success(data.nearResult)
                }
        }
    }

    fun getSearchList(search: String) {
        viewModelScope.launch {
            _searchList.value = ApiState.Loading
            repository.getSearchList(search)
                .catch { e ->
                    _searchList.value = ApiState.Error(e.message)
                }.collect { data ->
                    _searchList.value = ApiState.Success(data.result)
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