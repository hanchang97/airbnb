package com.team16.airbnb.data.repository

import com.team16.airbnb.data.dto.near.NearResultResponseDto
import com.team16.airbnb.data.dto.near.toNearResult
import com.team16.airbnb.data.model.SearchData
import com.team16.airbnb.data.model.home.NearResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class BaseHomeRepository {

    fun getInfo(info: Flow<NearResultResponseDto>) = flow {
        info.map {
            it.toNearResult()
        }.collect {
            emit(it)
        }
    }

    abstract fun getRecommendTheme(): Flow<NearResultResponse>

    abstract fun getHeroInfo(): Flow<NearResultResponse>

    abstract fun getNearList(): Flow<NearResultResponse>

    abstract fun getSearchList(search: String): Flow<SearchData>
}