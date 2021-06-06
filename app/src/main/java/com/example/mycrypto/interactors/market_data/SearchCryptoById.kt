package com.example.mycrypto.interactors.market_data

import com.example.coingechkoproject.cash.MarketDao
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.coingechkoproject.cash.model.MarketEntityMapper
import com.example.coingechkoproject.domain.CoinDtoMapper
import com.example.coingechkoproject.domain.data.DataState
import com.example.coingechkoproject.domain.model.MarketData
import com.example.coingechkoproject.network.NetworkService
import com.example.coingechkoproject.network.model.CoinMarketsDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchCryptoById(
    private val networkService: NetworkService,
    private val dtoMapper: CoinDtoMapper,
    private val marketDao: MarketDao,
    private val marketEntityMapper: MarketEntityMapper,
)
{
    lateinit var coin: MarketData

    //poluchit market data i zapihnut v ui
    fun execute(
        marketId: String,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<MarketData>> = flow {
        try {
            emit(DataState.loading())

            var cash = fromCash()

            if(cash.isNotEmpty()){
                val coin_cash = getMarketsByIds(marketId)
                val coin_domain = fromOneCoinEntity(coin_cash)
                emit(DataState.success(coin_domain))
            }
            // if the market is null, it means it was not in the cache for some reason. So get from network.
            else{

                if(isNetworkAvailable){
                    // get recipe from network
                    val market = fromNetwork() // dto -> domain

                    // insert into cache
                    marketDao.Markets(
                        // map domain -> entity
                        toEntity(market)
                    )

                }

                // get from cache
                val from_cash = getMarketsByIds(marketId = marketId)

                val coin = fromOneCoinEntity(from_cash)

                // emit and finish
                emit(DataState.success(coin))
            }

        }catch (e: Exception){
            emit(DataState.error<MarketData>(e.message?: "Unknown Error"))
        }
    }

    private suspend fun getMarketsByIds(marketId: String?): MarketEntity
    {
        return marketDao.getMarketsById(marketId)
    }

    private suspend fun getMarketsFromCache(): List<MarketEntity>
    {
        return marketDao.getAllMarkets()
    }

    private suspend fun getMarketsFromNetwork(): List<CoinMarketsDto> {
        return networkService.search()
    }

    private suspend fun fromCash(): List<MarketData>
    {
        val list: List<MarketEntity> = getMarketsFromCache()

        return list.map {
            marketEntityMapper.mapFromEntityModel(it)
        }
    }

    private suspend fun fromNetwork(): List<MarketData>
    {
        val list: List<CoinMarketsDto> = getMarketsFromNetwork()

        return list.map {
            dtoMapper.mapToDomainModel(it)
        }
    }

    private suspend fun toEntity(list: List<MarketData>): List<MarketEntity>
    {
        return list.map {
            marketEntityMapper.mapToEntityModel(it)
        }
    }

    private fun fromOneCoinEntity(coin: MarketEntity): MarketData
    {
           return marketEntityMapper.mapFromEntityModel(coin)
    }

}