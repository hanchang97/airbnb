package com.team16.airbnb.data.datasource

import com.team16.airbnb.data.network.HomeApi
import com.team16.airbnb.data.network.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeDataSource @Inject constructor(private val homeApi: HomeApi, private val searchApi: SearchApi) {


    fun getRecommendTheme() = flow {
        emit(searchApi.getNearList("recommendation"))
    }.flowOn(Dispatchers.IO)

    fun getHeroInfo() = flow {
        emit(searchApi.getNearList("hero"))
    }.flowOn(Dispatchers.IO)

    // 가까운 여행지 조회
    fun getNearList() = flow{
        emit(searchApi.getNearList("around"))
    }.flowOn(Dispatchers.IO)


}