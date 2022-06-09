package com.team16.airbnb.data.repository

import com.team16.airbnb.data.datasource.SearchDataSource
import com.team16.airbnb.data.dto.toSearchResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val dataSource: SearchDataSource) {
    fun getSearchAreaList(address: String) = dataSource.getSearchAreaList(address)

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
        dataSource.getSearchResult(
            address,
            checkIn,
            checkOut,
            minPrice,
            maxPrice,
            adult,
            child,
            infant
        ).map {
            it.map { data ->
                data.toSearchResult()
            }
        }.collect {
            emit(it)
        }
    }
}