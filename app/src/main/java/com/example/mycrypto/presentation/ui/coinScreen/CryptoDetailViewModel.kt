package com.example.mycrypto.presentation.ui.coinScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coingechkoproject.domain.model.MarketData
import com.example.coingechkoproject.presentation.util.ConnectivityManager
import com.example.coingechkoproject.presentation.util.TAG
import com.example.mycrypto.interactors.market_data.SearchCryptoById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val STATE_KEY_MARKET = "markets.state.markets.key"

@HiltViewModel
class CryptoDetailViewModel
@Inject
constructor(
    private val connectivityManager: ConnectivityManager,
    private val savedStateHandle: SavedStateHandle,
    private val searchCryptoById: SearchCryptoById,
) : ViewModel() {

    var marketId: String = "Bitcoin"

    var marketItems by mutableStateOf(listOf<MarketData>())
        private set


    val loading = mutableStateOf(false)

    val onLoad: MutableState<Boolean> = mutableStateOf(false)

    init {
        // restore if process dies
        savedStateHandle.get<String>(STATE_KEY_MARKET)?.let{ marketId ->
            onTriggerEvent(CryptoDetailStateEvent.GetMarketEvent(marketId))
        }
    }

    fun onTriggerEvent(event: CryptoDetailStateEvent) {
        viewModelScope.launch {
            try {
                when(event){
                    is CryptoDetailStateEvent.GetMarketEvent -> {
                        if(marketItems.isEmpty()){
                            Log.d(TAG, "onTriggerEvent: ")
                            newCryptoByIdSearch()
                        }
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private fun newCryptoByIdSearch() {
        searchCryptoById.execute("bitcoin", connectivityManager.isNetworkAvailable.value)
            .onEach { dataState ->

                loading.value = dataState.loading
                Log.d(TAG, "newCryptoByIdSearch LOADING: ${loading.value}")

                dataState.data?.let { list ->
                    marketItems = listOf(list)
                    Log.d(TAG, "newCryptoByIdSearch SUCCESS: $marketItems")
                }

                dataState.error?.let { error ->
                    Log.e(TAG, "newCryptoByIdSearch ERROR: ${error}")
                    //dialogQueue.appendErrorMessage("An Error Occurred", error)
                }
            }.launchIn(viewModelScope)
    }

}