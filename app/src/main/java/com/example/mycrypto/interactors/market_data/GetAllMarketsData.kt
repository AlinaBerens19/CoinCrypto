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

//create another object for DES ASC order!!!!!!!!!!!!
class GetAllMarketsData(
    private val networkService: NetworkService,
    private val dtoMapper: CoinDtoMapper,
    private val marketDao: MarketDao,
    private val marketEntityMapper: MarketEntityMapper,
)
{
    lateinit var list: List<MarketEntity>

    //poluchit market data i zapihnut v ui
    fun execute(
        isNetworkAvailable: Boolean,
        listOrder: Boolean
    ): Flow<DataState<List<MarketData>>> = flow {
        try {
            emit(DataState.loading())

            // just to show loading, cache is fast
            //delay(1000)

            val cash = fromCash(listOrder)

            if(cash.isNotEmpty()){
                emit(DataState.success(cash))
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
            }

        }catch (e: Exception){
            emit(DataState.error<List<MarketData>>(e.message?: "Unknown Error"))
        }
    }


    private suspend fun getMarketsFromCacheDes(): List<MarketEntity>
    {
        return marketDao.getMarketsDescLimit()
    }

    private suspend fun getMarketsFromCacheAsc(): List<MarketEntity>
    {
        return marketDao.getMarketsAscLimit()
    }

    private suspend fun getMarketsFromNetwork(): List<CoinMarketsDto> {
        return networkService.search()
    }

    private suspend fun fromCash(listOrder: Boolean): List<MarketData>
    {
        if (listOrder){
        list = getMarketsFromCacheDes()
        }
        else
        {
            list = getMarketsFromCacheAsc()
        }

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

    private fun toEntity(list: List<MarketData>): List<MarketEntity>
    {
        return list.map {
            marketEntityMapper.mapToEntityModel(it)
        }
    }


}