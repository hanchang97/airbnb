package com.team16.airbnb.data.model.home

data class NearResultResponse(
    val nearResult: List<NearResult> = listOf()
) {
    data class NearResult(
        val content: String,
        val imageUrl: String,
        val title: String
    )
}

