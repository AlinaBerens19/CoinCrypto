package com.example.mycrypto.presentation.components.chart.model

import com.google.gson.annotations.SerializedName


data class ChartModel(
    @SerializedName("prices")
    val prices: List<List<PriceModel>>
)




