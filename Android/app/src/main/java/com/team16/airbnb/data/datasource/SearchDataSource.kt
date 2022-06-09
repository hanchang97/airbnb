package com.team16.airbnb.data.datasource

import com.team16.airbnb.data.network.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor( private val searchApi: SearchApi) {

    // 가까운 여행지 조회
    fun getSearchAreaList(address: String) = flow{
        emit(searchApi.getSearchAreaList(address))
    }.flowOn(Dispatchers.IO)
}