package com.example.mycrypto.presentation.components.chart.model

import com.google.gson.annotations.SerializedName


data class PriceModel(

    val timestamp: Long,

    val price: Double
)