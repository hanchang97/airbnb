package com.team16.airbnb.data.datasource

import com.team16.airbnb.data.network.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor( private val searchApi: SearchApi) {

    // 가까운 여행지 조회
    fun getSearchAreaList(address: String) = flow{
        emit(searchApi.getSearchAreaList(address))
    }.flowOn(Dispatchers.IO)

    fun getSearchResult(
        address: String,
        checkIn: String,
        checkOut: String,
        minPrice: Int,
        maxPrice: Int,
        adult: Int,
        child: Int,
        infant: Int
    ) = flow {
        emit(
            searchApi.getSearchResult(
                address,
                checkIn,
                checkOut,
                minPrice,
                maxPrice,
                adult,
                child,
                infant
            )
        )
    }.flowOn(Dispatchers.IO)
}