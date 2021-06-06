package com.example.coingechkoproject.network

import com.example.coingechkoproject.network.model.CoinMarketsDto
import com.example.coingechkoproject.network.response.MarketResponse
import com.example.mycrypto.presentation.components.chart.model.ChartModel
import okhttp3.OkHttp

import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    //zasunula dollar v GET. POCHINIT!!!!!!!!!!!
    @GET("coins/markets?vs_currency=usd")
    suspend fun search(
       //@Query("ids") ids : String,
    ): List<CoinMarketsDto>

    //zasunula dollar v GET. POCHINIT!!!!!!!!!!!
    @GET("coins/markets?vs_currency=usd")
    suspend fun searchById(
        @Query("ids") ids : String,
    ): CoinMarketsDto


    @GET("coins/bitcoin/market_chart?vs_currency=usd&days=14")
    suspend fun getMarketChat(
        // @Query("vs_currency ") vs_currency : String,
        //@Query("id") id : String,
        // @Query("days") days: Int,
    ): ChartModel


}