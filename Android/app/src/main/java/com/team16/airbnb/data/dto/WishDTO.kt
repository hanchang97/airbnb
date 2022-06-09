package com.team16.airbnb.data.dto


import com.team16.airbnb.data.model.WishData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WishDTO(
    @SerialName("result")
    val result: List<Result>?
) {
    @Serializable
    data class Result(
        @SerialName("imageThumnail")
        val imageThumnail: String?,
        @SerialName("price")
        val price: Int?,
        @SerialName("review")
        val review: Int?,
        @SerialName("roomId")
        val roomId: Int?,
        @SerialName("roomName")
        val roomName: String?,
        @SerialName("star")
        val star: Double?
    )
}

fun WishDTO.toWishData(): WishData {
    val list = this.result?.map { (imageThumnail, price, review, roomId, roomName, star) ->
        WishData.ResultData(
            imageThumnail.orEmpty(),
            requireNotNull(price),
            review ?: 0,
            requireNotNull(roomId),
            requireNotNull(roomName),
            star ?: 0.0
        )
    }.orEmpty()

    return WishData(list)
}