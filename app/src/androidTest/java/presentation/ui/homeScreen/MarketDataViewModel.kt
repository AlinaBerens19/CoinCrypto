package com.example.coingechkoproject.presentation.ui.homeScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.coingechkoproject.interactors.market_data.SearchMarketData

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class MarketDataViewModel
@Inject
constructor(
    private val searchMarketData: SearchMarketData
) : ViewModel() {

    var cash: List<MarketEntity> = ArrayList()

    var network_data: MarketEntity? = null


    //dostat fotki
     fun fetchPhotoFromCash(): List<String>? {
            return cash.map {
                it.image!!
            }
        }


     fun fetchMarketsIds(): List<String>?
    {
            return cash.map {
                it.id
            }
        }


     fun fetchMarketsCurrentPrices(): List<Double>?
    {
            return cash.map {
                it.currentPrice
            }
        }

    fun fetchMarketsTotalVolume(): List<Double>?
    {
        return cash.map {
            it.totalVolume
        }
    }


    suspend fun invokeEntityModel()
    {
       // cash = searchMarketData.toEntityModel()
        cash = searchMarketData.fetchCoinsFromCash()!!
        Log.d("TAG", "invokeEntityModel: ${cash.size}")
    }

    fun getCoinById(coinId: String?)
    {
        viewModelScope.launch {
            cash = searchMarketData.execute(coinId, connectivityManager.isNetworkAvailable.value)!!
            Log.d("TAG", "getCoinById1: $cash")
        }
        Log.d("TAG", "getCoinById2: $cash")
    }

    fun rewriteCash()
    {
        viewModelScope.launch {
            try {
                cash = searchMarketData.rewriteCash()!!
                Log.d("TAG", "invokeEntityModel: ${cash.size}")
            }
            catch (e: Exception){
                Log.e("TAG", "Null cash exception: ${e.toString()}", )
            }

        }
    }

    class RemoteDataSource(
        private val coinApi: CoinApi,
        private val refreshIntervalMs: Long = 5000
    ) {
        val latestMarkets: Flow<List<MarketEntity>> = flow {
            while(true) {
                val latestMarkets = coinApi.fetchLatestNews()
                emit(latestMarkets) // Emits the result of the request to the flow
                delay(refreshIntervalMs) // Suspends the coroutine for some time
            }
        }
    }

    // Interface that provides a way to make network requests with suspend functions
    interface CoinApi {
        suspend fun fetchLatestNews(): List<MarketEntity>
    }


}


