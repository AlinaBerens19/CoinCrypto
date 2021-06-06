package com.example.coingechkoproject.presentation.ui.homeScreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.coingechkoproject.presentation.components.onFavoriteButtonPress
import com.example.mycrypto.R
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



@Composable
fun MarketDataScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    coinId: String?,
    viewModel: MarketDataViewModel)

{
    SearchBar_ViewModel(viewModel, "bitcoin")

    Log.d("MyDebug", "MarketDataScreen: start from market screen")
}


@Composable
fun ImageListItem_ViewModel(coin: String?, url: String?, price: Double?, total_volume: Double?) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.dogecoin_doge_price_and_reviews),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("$coin", style = MaterialTheme.typography.subtitle1)

        Spacer(Modifier.width(10.dp))
        Text("$price USD", style = MaterialTheme.typography.subtitle2)

        Spacer(Modifier.width(10.dp))
        Text("$total_volume USD", style = MaterialTheme.typography.subtitle2)
    }
}


@Composable
fun ScrollingList_ViewModel(viewModel: MarketDataViewModel) {

    var coins : List<String>?= null
    var urls : List<String>?= null
    var prices : List<Double>?= null
    var total_volume : List<Double>?= null
    runBlocking {
        val job1 = launch {
            viewModel.invokeEntityModel()
            coins = viewModel.fetchMarketsIds()
            urls = viewModel.fetchPhotoFromCash()
            prices = viewModel.fetchMarketsCurrentPrices()
            total_volume = viewModel.fetchMarketsTotalVolume()
            println("Job1 launched")
        }
        job1.invokeOnCompletion {
            Log.d("COINS", "COINS: ${coins}")
            Log.d("URLS", "URLS: ${urls}")
            Log.d("PRICES", "PRICES: ${prices}")
            Log.d("PRICES", "PRICES: ${total_volume}")
            println("Job1 completed") }

    }

    val listSize = 30
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {

            LazyColumn(
                state = scrollState,
//                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//                    modifier = Modifier.padding(8.dp)
            ) {
                items(listSize) {
                    if (coins != null && urls != null && prices != null) {
                        var a = 0
                        for (i in coins!!) {
                            val coin = coins!![a]
                            val url = urls!![a]
                            val price = prices!![a]
                            val volume = total_volume!![a]
                            ImageListItem_ViewModel(coin, url,price, volume)
                            a++
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchingList_ViewModel(viewModel: MarketDataViewModel, coinId: String?) {


    var coins : List<String>?= null
    var urls : List<String>?= null
    var prices : List<Double>?= null
    var total_volume : List<Double>?= null

    viewModel.getCoinById(coinId)
    coins = viewModel.fetchMarketsIds()
    urls = viewModel.fetchPhotoFromCash()
    prices = viewModel.fetchMarketsCurrentPrices()
    total_volume = viewModel.fetchMarketsTotalVolume()
    println("Job1 launched")


    val listSize = 30
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {

            LazyColumn(
                state = scrollState,
//                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//                    modifier = Modifier.padding(8.dp)
            ) {
                items(listSize) {
                    if (coins != null && urls != null && prices != null) {
                        var a = 0
                        for (i in coins!!) {
                            val coin = coins!![a]
                            val url = urls!![a]
                            val price = prices!![a]
                            val volume = total_volume!![a]
                            ImageListItem_ViewModel(coin, url, price, volume)
                            a++
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar_ViewModel(viewModel: MarketDataViewModel, coinId: String) {


    var flag = true

//    val scaffoldState = rememberScaffoldState(
//        rememberDrawerState(DrawerValue.Closed)
//    )

    Scaffold(
        topBar =
        {
            TopAppBar(
                title = {
                    Text(
                        text = "Your crypto",
                        color = Color.White)
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null // decorative element
                    )

                },
                backgroundColor = Color.Blue,

                contentColor = Color.White,

                elevation = 12.dp,

                actions = {
                    IconButton(onClick = { viewModel.rewriteCash() }) {
                        Icon(Icons.Filled.Refresh, contentDescription = null)
                        flag = true
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(onClick = {  viewModel.getCoinById(coinId) }) {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(onClick = { onFavoriteButtonPress() }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        },

//        snackbarHost = {
//            scaffoldState.snackbarHostState
//        },
//
//        drawerGesturesEnabled = true,

        content = {

        ScrollingList_ViewModel(viewModel)         

        },

        //scaffoldState = scaffoldState,

        floatingActionButtonPosition = FabPosition.End,

        floatingActionButton = { MyFloatingButton() },

        isFloatingActionButtonDocked = true,
    )  

}


//crash of floating button!!!!
@Composable
fun MyFloatingButton() {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        scope.launch {
            scrollState.animateScrollToItem(0)
        }
    }) {
        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
    }
}