package com.example.mycrypto.presentation.ui.coinScreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coingechkoproject.presentation.ui.homeScreen.MarketDataViewModel
import com.example.mycrypto.presentation.components.CoinDetailCard
import com.example.mycrypto.presentation.components.chart.LineChart
import com.example.mycrypto.presentation.components.chart.line.LineChartData
import com.example.mycrypto.presentation.components.chart.line.renderer.SolidLineDrawer
import com.example.mycrypto.presentation.components.chart.point.FilledCircularPointDrawer
import com.example.mycrypto.presentation.components.chart.simpleChartAnimation
import com.example.mycrypto.presentation.components.chart.xaxis.SimpleXAxisDrawer
import com.example.mycrypto.presentation.components.chart.yaxis.SimpleYAxisDrawer


private const val DividerLengthInDegrees = 1.8f

 var points: List<LineChartData.Point> = ArrayList<LineChartData.Point>()


@Composable
fun DetailCoinScreen(
    viewModel: MarketDataViewModel,
    coinId: String,
    onCoinByIdSearch: Unit
)
{
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            }
        ) {
            Column (
                modifier = Modifier.fillMaxSize()
            ){
                CoinDetailCard(viewModel, coinId, onCoinByIdSearch)
            }
        }
    }




