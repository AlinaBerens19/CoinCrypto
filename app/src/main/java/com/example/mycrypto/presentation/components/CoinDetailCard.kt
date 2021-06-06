package com.example.mycrypto.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.coingechkoproject.presentation.ui.homeScreen.MarketDataViewModel
import com.example.mycrypto.presentation.components.chart.LineChart
import com.example.mycrypto.presentation.components.chart.line.LineChartData
import com.example.mycrypto.presentation.components.chart.line.renderer.SolidLineDrawer
import com.example.mycrypto.presentation.components.chart.point.FilledCircularPointDrawer
import com.example.mycrypto.presentation.components.chart.simpleChartAnimation
import com.example.mycrypto.presentation.components.chart.xaxis.SimpleXAxisDrawer
import com.example.mycrypto.presentation.components.chart.yaxis.SimpleYAxisDrawer
import com.example.mycrypto.presentation.ui.coinScreen.points


@Composable
fun CoinDetailCard(
    viewModel: MarketDataViewModel,
    coinId: String,
    onCoinByIdSearch: Unit
) {
            Card(backgroundColor = Color.LightGray,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),) {
                MyLineChartParent()
            }

            Spacer(Modifier.height(16.dp))
            Box()
            {
                //MyLineChartParent()
            }
            Spacer(Modifier.height(16.dp))
            Box() {
                Text(text = "Price $coinId - ${viewModel.marketItem?.currentPrice}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
                Text(text = "Market Cap - ${viewModel.marketItem?.marketCap}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
                Text(text = "24h Low - ${viewModel.marketItem?.low24h}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
                Text(text = "24h High - ${viewModel.marketItem?.high24h}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
                Text(text = "Total volume - ${viewModel.marketItem?.totalVolume}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
            Text(text = "Total supply - ${viewModel.marketItem?.totalSupply}", style = MaterialTheme.typography.subtitle2)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box() {
                Text(text = "Volume / Market Cap - ${viewModel.marketItem?.marketCap}", style = MaterialTheme.typography.subtitle2)
            }
        }

fun point()
{
    var i = 10
    points
}

@Composable
fun MyLineChartParent() {


    Log.d("TAG", "MyLineChartParent: ${points}")

        points = listOf(
            LineChartData.Point(randomYValue(), "Label1"),
            LineChartData.Point(randomYValue(), "Label2"),
            LineChartData.Point(randomYValue(), "Label3"),
            LineChartData.Point(randomYValue(), "Label4"),
            LineChartData.Point(randomYValue(), "Label5"),
            LineChartData.Point(randomYValue(), "Label6"),
            LineChartData.Point(randomYValue(), "Label7")
        )


    LineChart(
        lineChartData = LineChartData(points, 20f, true),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}

private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f

