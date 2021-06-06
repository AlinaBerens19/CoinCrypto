package com.example.mycrypto.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coingechkoproject.domain.model.MarketData
import com.example.coingechkoproject.presentation.components.HomeCard
import com.example.mycrypto.R
import com.example.mycrypto.Screen
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.ExperimentalCoroutinesApi



@ExperimentalCoroutinesApi
@Composable
fun ScrollingMarketsList(markets: List<MarketData>,
                         onNavigateToMarketDetailScreen:(String)-> Unit,
) {

    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {

        LazyColumn(
           // state = scrollState
        ) {
            itemsIndexed(
                items = markets
            ) { index, market ->
                for (coin in markets) {
                    market.id = coin.id
                    market.image = coin.image
                    market.currentPrice = coin.currentPrice
                    CoinCard(
                        marketData = market,
                        onClick = {
                            val route = Screen.DetailCoin.name + "/${coin.id}"
                            Log.d("TAG", "ScrollingMarketsList: $route")
                            onNavigateToMarketDetailScreen(coin.id)
                        }
                    )
                }
            }
        }
    }
}


