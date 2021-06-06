package com.example.coingechkoproject.network.data

import com.google.gson.annotations.SerializedName


data class Market(
    val name: String,
    val identifier: String,
    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean = false
)