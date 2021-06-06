package com.example.coingechkoproject.domain.model

import com.example.coingechkoproject.network.data.Roi
import com.google.gson.annotations.SerializedName


data class MarketData (

    var id: String,

    val symbol: String? = null,

    val name: String? = null,

    var image: String? = null,

    var currentPrice: Double = 0.0,

    val marketCap: Double = 0.0,

    val marketCapRank: Long = 0,

    val fully_diluted_valuation: Double = 0.0,

    val totalVolume: Double = 0.0,

    val high24h: Double = 0.0,

    val low24h: Double = 0.0,

    val priceChange24h: Double = 0.0,

    val priceChangePercentage24h: Double = 0.0,

    val marketCapChange24h: Double = 0.0,

    val marketCapChangePercentage24h: Double = 0.0,

    val circulatingSupply: Double? = 0.0,

    val totalSupply: Double? = 0.0,

    val max_supply: Double? = 0.0,

    val ath: Double? = 0.0,

    val athChangePercentage: Double? = 0.0,

    val athDate: String? = null,

    val atl: Double? = 0.0,

    val atl_change_percentage: Double? = 0.0,

    val atl_date: String? = null,

    val roi: Roi? = null,

    val lastUpdated: String? = null,

    )