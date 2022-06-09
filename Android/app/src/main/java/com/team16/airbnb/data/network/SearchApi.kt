package com.team16.airbnb.data.network

import com.team16.airbnb.data.dto.near.NearResultResponseDto
import com.team16.airbnb.data.dto.searcharea.SearchAreaResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("main")
    suspend fun getNearList(@Query("type") type: String): NearResultResponseDto

    @GET("search")
    suspend fun getSearchAreaList(@Query("address") address: String): SearchAreaResponseDto
}