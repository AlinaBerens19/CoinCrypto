package com.example.coingechkoproject.network.data

import com.google.gson.annotations.SerializedName


data class MarketChart(
    val prices: List<List<String>>,
//    @SerializedName("market_caps")
//    val marketCaps: List<List<String>>,
//    @SerializedName("total_volumes")
//    val totalVolumes: List<List<String>>
)