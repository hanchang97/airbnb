package com.team16.airbnb.data.dto.near

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NearResultResponseDto(
    @SerialName("result")
    val nearResult: List<NearResult>? = listOf()
)