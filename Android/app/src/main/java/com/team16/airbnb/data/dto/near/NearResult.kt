package com.team16.airbnb.data.dto.near

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NearResult(
    @SerialName("content")
    val content: String? = "",
    @SerialName("imageUrl")
    val imageUrl: String? = "",
    @SerialName("title")
    val title: String? = ""
)