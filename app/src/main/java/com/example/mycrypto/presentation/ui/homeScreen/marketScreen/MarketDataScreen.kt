package com.example.coingechkoproject.presentation.ui.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mycrypto.presentation.components.ScrollingMarketsList
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun MarketDataScreen(
    //isNetworkAvailable: Boolean,
    //onCoinByIdSearch: (String) -> Unit,
    viewModel: MarketDataViewModel,
    onNavigateToMarketScreen: (String) -> Unit,
) {
    Scaffold {
        //innerPadding ->
        ScrollingMarketsList(
            viewModel.marketItems,
            onNavigateToMarketScreen
        )
    }
}

