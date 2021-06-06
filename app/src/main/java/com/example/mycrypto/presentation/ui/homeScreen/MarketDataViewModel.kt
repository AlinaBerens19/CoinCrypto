package com.example.coingechkoproject.presentation.ui.homeScreen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*

import com.example.coingechkoproject.domain.model.MarketData
import com.example.coingechkoproject.interactors.market_data.SearchMarketData
import com.example.coingechkoproject.presentation.util.ConnectivityManager
import com.example.coingechkoproject.presentation.util.TAG
import com.example.mycrypto.interactors.market_data.GetAllMarketsData
import com.example.mycrypto.interactors.market_data.SearchCryptoById
import com.example.mycrypto.presentation.ui.homeScreen.MarketsDataStateEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

//PROVERIT CHTO ETO ZA KONSTANTA!!!!!!
const val STATE_KEY_QUERY = "market.state.query.key"
const val STATE_KEY_LIST_POSITION = "market.state.query.list_position"

@HiltViewModel
class MarketDataViewModel
@Inject
constructor(
    private val searchMarketData: GetAllMarketsData,
    private val searchCryptoById: SearchCryptoById,
    private val connectivityManager: ConnectivityManager,
    private val savedStateHandle: SavedStateHandle,
    private val searchChartData: SearchMarketData,
) : ViewModel() {

    var marketItems by mutableStateOf(listOf<MarketData>())
        private set

     var marketItem: MarketData? = null

    //val marketItems: MutableState<List<MarketData>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    val listOrder = mutableStateOf(false)

    val query = mutableStateOf("")

    var recipeListScrollPosition = 0


    init {
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }

        if (recipeListScrollPosition != 0) {
            onTriggerEvent(MarketsDataStateEvent.RestoreStateEvent)
        } else {
            onTriggerEvent(MarketsDataStateEvent.NewSearchEvent)
        }
    }

    private fun setListScrollPosition(p: Int) {

    }

    private fun setQuery(q: String) {

    }

    fun getChartData()
    {
        viewModelScope.launch {
            try {
                val a = searchChartData.getMarketChart()
                Log.d(TAG, "getChartData: ${a}")
            }
            catch (e: Exception)
            {
                Log.e(TAG, "onCreate: ${e}", )
            }

        }
    }

    private fun onTriggerEvent(event: MarketsDataStateEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is MarketsDataStateEvent.NewSearchEvent -> {
                        newSearch()
                    }

                    is MarketsDataStateEvent.RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }

    private fun restoreState() {
        //TODO("Not yet implemented")
    }

     private fun newSearch() {
        searchMarketData.execute(connectivityManager.isNetworkAvailable.value, true)
            .onEach { dataState ->

                loading.value = dataState.loading
                Log.d(TAG, "newSearch LOADING: ${loading.value}")

                dataState.data?.let { list ->
                    marketItems = list
                    Log.d(TAG, "newSearch SUCCESS: $marketItems")
                }

                dataState.error?.let { error ->
                    Log.e(TAG, "newSearch ERROR: ${error}")
                    //dialogQueue.appendErrorMessage("An Error Occurred", error)
                }
            }.launchIn(viewModelScope)
    }


     fun newSearchById(coinId: String) {
         val job: Job = searchCryptoById.execute(coinId, connectivityManager.isNetworkAvailable.value)
             .onEach { dataState ->

                 loading.value = dataState.loading
                 Log.d(TAG, "newSearchById LOADING: ${loading.value}")

                 dataState.data?.let { list ->
                     marketItem = list
                     Log.d(TAG, "newSearchById SUCCESS: $marketItem")
                 }

                 dataState.error?.let { error ->
                     Log.e(TAG, "newSearchById ERROR: ${error}")
                     //dialogQueue.appendErrorMessage("An Error Occurred", error)
                 }
             }.launchIn(viewModelScope)

         if (job.isCompleted)
         {
             Log.d(TAG, "newSearchById: JOB COMPLETED")
             job.cancel()
         }
     }
}




