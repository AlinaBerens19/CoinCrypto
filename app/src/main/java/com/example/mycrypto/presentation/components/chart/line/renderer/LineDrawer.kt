package com.example.mycrypto.presentation.components.chart.line.renderer


import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope


interface LineDrawer {
    fun drawLine(
        drawScope: DrawScope,
        canvas: Canvas,
        linePath: Path
    )
}