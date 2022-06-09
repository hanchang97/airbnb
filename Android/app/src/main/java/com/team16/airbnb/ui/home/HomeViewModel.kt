package com.team16.airbnb.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team16.airbnb.common.ApiState
import com.team16.airbnb.data.model.SearchResult
import com.team16.airbnb.data.model.home.NearResultResponse
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

    private val _heroInfo = MutableStateFlow<List<NearResultResponse.NearResult>>(emptyList())
    val heroInfo = _heroInfo

    private val _recommendTheme = MutableStateFlow<List<NearResultResponse.NearResult>>(emptyList())
    val recommendTheme = _recommendTheme

    private val _nearListStateFlow =
        MutableStateFlow<ApiState<List<NearResultResponse.NearResult>>>(ApiState.Empty)
    val nearListStateFlow: StateFlow<ApiState<List<NearResultResponse.NearResult>>> =
        _nearListStateFlow

    private val _searchResult = MutableStateFlow<ApiState<List<SearchResult>>>(ApiState.Empty)
    val searchResult: StateFlow<ApiState<List<SearchResult>>> = _searchResult

    private val _searchAreaListStateFlow = MutableStateFlow<ApiState<List<String>>>(ApiState.Empty)
    val searchAreaListStateFlow: StateFlow<ApiState<List<String>>> =_searchAreaListStateFlow

    var search = ""

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

    fun getSearchResult(
        address: String,
        checkIn: String,
        checkOut: String,
        minPrice: Int,
        maxPrice: Int,
        adult: Int,
        child: Int,
        infant: Int
    ) {
        viewModelScope.launch {
            _searchResult.value = ApiState.Loading
            Log.d("TAG", "viewmodel $address")
            searchRepository.getSearchResult(
                address, checkIn, checkOut, minPrice, maxPrice, adult, child, infant
            ).catch { e ->
                _searchResult.value = ApiState.Error(e.message)
            }.collect{ data ->
                _searchResult.value = ApiState.Success(data)
            }
        }
    }
}