package com.example.coingechkoproject.network.data

import com.google.gson.annotations.SerializedName


data class SparklineIn7d(

    @SerializedName("price")
    val price: List<Double>? = null
)