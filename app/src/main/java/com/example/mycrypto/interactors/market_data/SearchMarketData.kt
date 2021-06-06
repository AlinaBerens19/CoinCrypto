package com.example.coingechkoproject.interactors.market_data

import android.util.Log
import com.example.coingechkoproject.cash.MarketDao
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.coingechkoproject.cash.model.MarketEntityMapper
import com.example.coingechkoproject.network.NetworkService
import com.example.coingechkoproject.network.model.CoinMarketsDto
import com.example.coingechkoproject.domain.CoinDtoMapper
import com.example.coingechkoproject.domain.model.MarketData
import java.lang.Exception


class SearchMarketData(
    private val networkService: NetworkService,
    private val dtoMapper: CoinDtoMapper,
    private val marketDao: MarketDao,
    private val marketEntityMapper: MarketEntityMapper,
) {
    suspend fun getMarketChart()
    {
        networkService.getMarketChat()
    }

}
