package com.team16.airbnb.data.dto.search


import com.team16.airbnb.data.model.SearchData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDTO(
    @SerialName("result")
    val result: List<String>?
)

fun SearchDTO.toSearchData() = SearchData(this.result.orEmpty())
