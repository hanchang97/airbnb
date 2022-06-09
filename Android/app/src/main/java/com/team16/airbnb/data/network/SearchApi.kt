package com.team16.airbnb.data.network

import com.team16.airbnb.data.dto.SearchResultDTO
import com.team16.airbnb.data.dto.near.NearResultResponseDto
import com.team16.airbnb.data.dto.searcharea.SearchAreaResponseDto
import com.team16.airbnb.data.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {

    @GET("main")
    suspend fun getNearList(@Query("type") type: String): NearResultResponseDto

    @GET("search")
    suspend fun getSearchAreaList(@Query("address") address: String): SearchAreaResponseDto

    @GET("search/rooms")
    suspend fun getSearchResult(
        @Query(value = "address", encoded = true) address: String,
        @Query("checkIn") checkIn: String,
        @Query("checkOut") checkOut: String,
        @Query("minPrice") minPrice: Int,
        @Query("maxPrice") maxPrice: Int,
        @Query("adult") adult: Int,
        @Query("child") child: Int,
        @Query("infant") infant: Int
    ): List<SearchResultDTO>

}