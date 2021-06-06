package com.example.mycrypto.presentation.components.chart.line.renderer



import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

interface LineShader {
    fun fillLine(
        drawScope: DrawScope,
        canvas: Canvas,
        fillPath: Path
    )
}