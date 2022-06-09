package com.team16.airbnb.data.dto.near

import com.team16.airbnb.data.model.home.NearResultResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NearResultResponseDto(
    @SerialName("result")
    val nearResultDTO: List<NearResultDTO>? = listOf()
) {
    @Serializable
    data class NearResultDTO(
        @SerialName("content")
        val content: String? = "",
        @SerialName("imageUrl")
        val imageUrl: String? = "",
        @SerialName("title")
        val title: String? = ""
    )
}

fun NearResultResponseDto.toNearResult(): NearResultResponse {
    val list = this.nearResultDTO?.map { (content, imageUrl, title) ->
        NearResultResponse.NearResult(
            content.orEmpty(),
            imageUrl.orEmpty(),
            title ?: "이름 없음"
        )
    }.orEmpty()

    return NearResultResponse(list)
}