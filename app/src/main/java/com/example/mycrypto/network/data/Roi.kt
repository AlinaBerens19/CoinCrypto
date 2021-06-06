package com.example.coingechkoproject.network.data

import com.google.gson.annotations.SerializedName


//find right import

data class Roi(
    @SerializedName("times")
    val times: Float = 0f,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("percentage")
    val percentage: Float = 0f
)