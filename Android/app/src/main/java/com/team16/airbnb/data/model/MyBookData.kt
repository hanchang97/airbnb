package com.team16.airbnb.data.model

data class MyBookData(
    val result: List<Result>
) {

    data class Result(
        val address: List<Address>,
        val checkIn: String,
        val checkOut: String,
        val imageThumnail: String,
        val roomId: Int,
        val roomName: String
    ) {

        data class Address(
            val city: String,
            val detail: String,
            val district: String,
            val region: String
        )

    }
}
