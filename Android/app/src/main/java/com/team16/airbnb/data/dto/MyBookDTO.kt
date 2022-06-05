package com.team16.airbnb.data.dto


import com.team16.airbnb.data.model.MyBookData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyBookDTO(
    @SerialName("result")
    val result: List<Result>?
) {

    @Serializable
    data class Result(
        @SerialName("address")
        val address: List<Address>?,
        @SerialName("checkIn")
        val checkIn: String?,
        @SerialName("checkOut")
        val checkOut: String?,
        @SerialName("imageThumnail")
        val imageThumnail: String?,
        @SerialName("roomId")
        val roomId: Int?,
        @SerialName("roomName")
        val roomName: String?
    ) {

        @Serializable
        data class Address(
            @SerialName("city")
            val city: String?,
            @SerialName("detail")
            val detail: String?,
            @SerialName("district")
            val district: String?,
            @SerialName("region")
            val region: String?
        )

    }
}

fun MyBookDTO.toMyBookData(): MyBookData {
    val list = mutableListOf<MyBookData.Result>()
    this.result?.forEach {
        val address: List<Address>?,
        val checkIn: String?,
        val checkOut: String?,
        val imageThumnail: String?,
        val roomId: Int?,
        val roomName: String?
    }
    return MyBookData(list)
}
