package com.team16.airbnb.data.dto.searcharea

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchAreaResponseDto(
    @SerialName("result")
    val result: List<String>? = listOf()
)