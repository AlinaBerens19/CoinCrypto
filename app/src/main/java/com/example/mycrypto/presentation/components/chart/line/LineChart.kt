package com.example.mycrypto.presentation.components.chart


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateDrawableArea
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateFillPath
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateLinePath
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculatePointLocation
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateXAxisDrawableArea
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.calculateYAxisDrawableArea
import com.example.mycrypto.presentation.components.chart.line.LineChartUtils.withProgress
import com.example.mycrypto.presentation.components.chart.line.LineChartData
import com.example.mycrypto.presentation.components.chart.line.renderer.LineDrawer
import com.example.mycrypto.presentation.components.chart.line.renderer.LineShader
import com.example.mycrypto.presentation.components.chart.line.renderer.NoLineShader
import com.example.mycrypto.presentation.components.chart.line.renderer.SolidLineDrawer
import com.example.mycrypto.presentation.components.chart.point.FilledCircularPointDrawer
import com.example.mycrypto.presentation.components.chart.point.PointDrawer
import com.example.mycrypto.presentation.components.chart.xaxis.SimpleXAxisDrawer
import com.example.mycrypto.presentation.components.chart.xaxis.XAxisDrawer
import com.example.mycrypto.presentation.components.chart.yaxis.SimpleYAxisDrawer
import com.example.mycrypto.presentation.components.chart.yaxis.YAxisDrawer


@Composable
fun LineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = simpleChartAnimation(),
    pointDrawer: PointDrawer = FilledCircularPointDrawer(),
    lineDrawer: LineDrawer = SolidLineDrawer(),
    lineShader: LineShader = NoLineShader,
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    horizontalOffset: Float = 5f
) {
    check(horizontalOffset in 0f..25f) {
        "Horizontal offset is the % offset from sides, " +
                "and should be between 0%-25%"
    }

    val transitionAnimation = remember(lineChartData.points) { Animatable(initialValue = 0f) }

    LaunchedEffect(lineChartData.points) {
        transitionAnimation.snapTo(0f)
        transitionAnimation.animateTo(1f, animationSpec = animation)
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val yAxisDrawableArea = calculateYAxisDrawableArea(
                xAxisLabelSize = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisDrawableArea = calculateXAxisDrawableArea(
                yAxisWidth = yAxisDrawableArea.width,
                labelHeight = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                offset = horizontalOffset
            )
            val chartDrawableArea = calculateDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                yAxisDrawableArea = yAxisDrawableArea,
                size = size,
                offset = horizontalOffset
            )

            // Draw the chart line.
            lineDrawer.drawLine(
                drawScope = this,
                canvas = canvas,
                linePath = calculateLinePath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                )
            )

            lineShader.fillLine(
                drawScope = this,
                canvas = canvas,
                fillPath = calculateFillPath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                )
            )

            lineChartData.points.forEachIndexed { index, point ->
                withProgress(
                    index = index,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                ) {
                    pointDrawer.drawPoint(
                        drawScope = this,
                        canvas = canvas,
                        center = calculatePointLocation(
                            drawableArea = chartDrawableArea,
                            lineChartData = lineChartData,
                            point = point,
                            index = index
                        )
                    )
                }
            }

            // Draw the X Axis line.
            xAxisDrawer.drawAxisLine(
                drawScope = this,
                drawableArea = xAxisDrawableArea,
                canvas = canvas
            )

            xAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = xAxisLabelsDrawableArea,
                labels = lineChartData.points.map { it.label }
            )

            // Draw the Y Axis line.
            yAxisDrawer.drawAxisLine(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea
            )

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea,
                minValue = lineChartData.minYValue,
                maxValue = lineChartData.maxYValue
            )
        }
    }
}