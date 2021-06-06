package com.example.coingechkoproject.cash.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coingechkoproject.network.data.Roi
import dagger.Provides
import javax.inject.Inject


@Entity(tableName = "market_data")
data class MarketEntity

(
    // Value from API
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    // Value from API
    @ColumnInfo(name = "symbol")
    var symbol: String? = null,

    // Value from API
    @ColumnInfo(name = "name")
    var name: String? = null,

    // Value from API
    @ColumnInfo(name = "image")
    var image: String? = null,

    // Value from API
    @ColumnInfo(name = "currentPrice")
    var currentPrice: Double = 0.0,

    // Value from API
    @ColumnInfo(name = "marketCap")
    var marketCap: Double = 0.0,


    @ColumnInfo(name = "marketCapRank")
    var marketCapRank: Long = 0,

    /**
     * Value from API
     */
    @ColumnInfo(name = "fully_diluted_valuation")
    var fully_diluted_valuation: Double = 0.0,

    /**
     * Value from API
     */
    @ColumnInfo(name = "totalVolume")
    var totalVolume: Double = 0.0,


    @ColumnInfo(name = "high24h")
    var high24h: Double = 0.0,

    @ColumnInfo(name = "low24h")
    var low24h: Double = 0.0,

    @ColumnInfo(name = "priceChange24h")
    var priceChange24h: Double = 0.0,

    @ColumnInfo(name = "priceChangePercentage24h")
    var priceChangePercentage24h: Double = 0.0,

    @ColumnInfo(name = "marketCapChange24h")
    var marketCapChange24h: Double = 0.0,

    @ColumnInfo(name = "marketCapChangePercentage24h")
    var marketCapChangePercentage24h: Double = 0.0,

    @ColumnInfo(name = "circulatingSupply")
    var circulatingSupply: Double? = 0.0,

    @ColumnInfo(name = "totalSupply")
    var totalSupply: Double? = 0.0,

    @ColumnInfo(name = "max_supply")
    var max_supply: Double? = 0.0,

    @ColumnInfo(name = "ath")
    var ath: Double? = 0.0,

    @ColumnInfo(name = "athChangePercentage")
    var athChangePercentage: Double? = 0.0,

    @ColumnInfo(name = "athDate")
    var athDate: String? = "",

    @ColumnInfo(name = "atl")
    var atl: Double? = 0.0,

    @ColumnInfo(name = "atl_change_percentage")
    var atl_change_percentage: Double? = 0.0,

//    @ColumnInfo(name = "roi")
//    var roi: Roi? = null,

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: String? = null,
)