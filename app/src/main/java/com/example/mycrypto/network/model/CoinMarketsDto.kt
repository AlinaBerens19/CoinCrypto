package com.example.coingechkoproject.network.model

import com.example.coingechkoproject.network.data.Roi
import com.google.gson.annotations.SerializedName

data class CoinMarketsDto(
    @SerializedName("id")
val id: String,
    @SerializedName("symbol")
val symbol: String? = null,
    @SerializedName("name")
val name: String? = null,
    @SerializedName("image")
val image: String? = null,
    @SerializedName("current_price")
val currentPrice: Double = 0.0,
    @SerializedName("market_cap")
val marketCap: Double = 0.0,
    @SerializedName("market_cap_rank")
val marketCapRank: Long = 0,
    @SerializedName("fully_diluted_valuation")
val fully_diluted_valuation: Double = 0.0,
    @SerializedName("total_volume")
val totalVolume: Double = 0.0,
    @SerializedName("high_24h")
val high24h: Double = 0.0,
    @SerializedName("low_24h")
val low24h: Double = 0.0,
    @SerializedName("price_change_24h")
val priceChange24h: Double = 0.0,
    @SerializedName("price_change_percentage_24h")
val priceChangePercentage24h: Double = 0.0,
    @SerializedName("market_cap_change_24h")
val marketCapChange24h: Double = 0.0,
    @SerializedName("market_cap_change_percentage_24h")
val marketCapChangePercentage24h: Double = 0.0,
    @SerializedName("circulating_supply")
val circulatingSupply: Double? = 0.0,
    @SerializedName("total_supply")
val totalSupply: Double? = 0.0,
    @SerializedName("max_supply")
val max_supply: Double? = 0.0,
    @SerializedName("ath")
val ath: Double? = 0.0,
    @SerializedName("ath_change_percentage")
val athChangePercentage: Double? = 0.0,
    @SerializedName("ath_date")
val athDate: String? = null,
    @SerializedName("atl")
val atl: Double? = 0.0,
    @SerializedName("atl_change_percentage")
val atl_change_percentage: Double? = 0.0,
    @SerializedName("atl_date")
val atl_date: String? = null,
    @SerializedName("roi")
val roi: Roi? = null,
    @SerializedName("last_updated")
val lastUpdated: String? = null,
)