package com.example.mycrypto.presentation.components.chart.ui



import androidx.compose.material.icons.Icons
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mycrypto.presentation.components.chart.point.FilledCircularPointDrawer
import com.example.mycrypto.presentation.components.chart.line.LineChartData
import com.example.mycrypto.presentation.components.chart.point.NoPointDrawer
import com.example.mycrypto.presentation.components.chart.point.PointDrawer


class LineChartDataModel {


    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
                LineChartData.Point(randomYValue(), "Label1"),
                LineChartData.Point(randomYValue(), "Label2"),
                LineChartData.Point(randomYValue(), "Label3"),
                LineChartData.Point(randomYValue(), "Label4"),
                LineChartData.Point(randomYValue(), "Label5"),
                LineChartData.Point(randomYValue(), "Label6"),
                LineChartData.Point(randomYValue(), "Label7")
            )
        )
    )
    var horizontalOffset by mutableStateOf(5f)
    var pointDrawerType by mutableStateOf(Icons.Filled)
    val pointDrawer: PointDrawer


    get() {
            return when (pointDrawerType) {
                //FontSynthesis.None -> NoPointDrawer
                Icons.Filled -> FilledCircularPointDrawer()
                //Hollow -> HollowCircularPointDrawer()

                else -> NoPointDrawer
            }
        }

    private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f


}