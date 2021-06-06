package com.example.mycrypto

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*

import com.example.coingechkoproject.presentation.ui.homeScreen.MarketDataScreen

import com.example.coingechkoproject.presentation.ui.homeScreen.MarketDataViewModel

import com.example.coingechkoproject.presentation.util.ConnectivityManager
import com.example.coingechkoproject.presentation.util.TAG
import com.example.mycrypto.presentation.components.HomeFloatingActionButton
import com.example.mycrypto.presentation.ui.coinScreen.DetailCoinScreen


import com.example.mycrypto.ui.theme.MyCryptoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject



@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {


    private val viewModel: MarketDataViewModel by viewModels()

    //private val viewModel: CryptoDetailViewModel by viewModels()
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalAnimationApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoApp()
        }


            viewModel.getChartData()


//        val instant: Instant = Instant.ofEpochMilli(1621829193943)
//        val a = Date.from(instant).toString()
//        Log.d(TAG, "timestamp3: $a")
    }




    @ExperimentalAnimationApi
    @ExperimentalCoroutinesApi
    @Composable
    fun CryptoApp() {

        val lazyListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()

        MyCryptoTheme {
            //val navController = rememberNavController()
            val backstackEntry = navController.currentBackStackEntryAsState()


            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "MyCrypto")
                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate(Screen.MarketData.name) }) {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    try {
                                        navController.navigate("test")
                                    } catch (e: Exception) {
                                        Log.e(TAG, "CryptoApp: $e")
                                    }
                                },
                            )
                            {
                                Icon(Icons.Filled.Search, contentDescription = null)
                            }

                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Favorite, contentDescription = null)
                            }
                        }

                    )
                },
                floatingActionButton = {
                    HomeFloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                Log.d(TAG, "CryptoApp: CLICKED")
                                navController.navigate(Screen.MarketData.name)
                            }
                        }
                    )
                }

            ) { innerPadding ->
                CryptoNavHost(navController, modifier = Modifier.padding(innerPadding), viewModel = viewModel)
            }
        }
    }
}

    @ExperimentalCoroutinesApi
    @Composable
    fun CryptoNavHost(
        navController: NavHostController,
        modifier: Modifier,
        viewModel: MarketDataViewModel
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.MarketData.name,
            modifier = modifier
        ) {

            composable(Screen.MarketData.name) {

                MarketDataScreen(
                    viewModel
                )
                { name ->
                    navigateToSingleAccount(navController, name)
                }

            }
            composable(
                route = Screen.DetailCoin.name + "/{marketId}",
                arguments = listOf(
                    navArgument("marketId") {
                        type = NavType.StringType
                    }
                )) { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("marketId")?.let {
                    DetailCoinScreen(
                        viewModel = viewModel,
                        coinId = it,
                        onCoinByIdSearch = getId(it, viewModel)
                    )

                }
            }
        }
    }

    private fun navigateToSingleAccount(navController: NavHostController, accountName: String) {
        navController.navigate("${Screen.DetailCoin.name}/$accountName")
    }

fun getId(coinId: String, viewModel: MarketDataViewModel)
{
    viewModel.newSearchById(coinId)    
}


