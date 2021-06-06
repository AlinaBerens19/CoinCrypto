package com.example.mycrypto.presentation.components.chart.pie




internal object PieChartUtils {
    fun calculateAngle(
        sliceLength: Float,
        totalLength: Float,
        progress: Float
    ): Float {
        return 360.0f * (sliceLength * progress) / totalLength
    }
}