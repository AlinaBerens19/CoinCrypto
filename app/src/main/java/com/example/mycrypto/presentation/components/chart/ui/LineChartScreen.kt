package com.example.mycrypto.presentation.components



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycrypto.presentation.components.chart.ChartScreenStatus
import com.example.mycrypto.presentation.components.chart.LineChart
import com.example.mycrypto.presentation.components.chart.ui.LineChartDataModel
import com.example.mycrypto.presentation.components.chart.point.PointDrawerType
import com.example.mycrypto.ui.theme.Margins.horizontal
import com.example.mycrypto.ui.theme.Margins.vertical
import com.example.mycrypto.ui.theme.Margins.verticalLarge


@Composable
fun LineChartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back to home")
                    }
                },
                title = { Text(text = "Line Chart") }
            )
        },
    ) { LineChartScreenContent() }
}

@Composable
fun LineChartScreenContent() {
    val lineChartDataModel = LineChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = vertical
        )
    ) {
        LineChartRow(lineChartDataModel)
        HorizontalOffsetSelector(lineChartDataModel)
        OffsetProgress(lineChartDataModel)
    }
}

@Composable
fun HorizontalOffsetSelector(lineChartDataModel: LineChartDataModel) {
    val selectedType = lineChartDataModel.pointDrawerType

    Row(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = verticalLarge
        ),
        verticalAlignment = CenterVertically
    ) {
        Text("Point Drawer")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontal, vertical = vertical)
                .align(CenterVertically)
        ) {
            PointDrawerType.values().forEach { type ->
                val color = if (selectedType == Icons.Filled) {
                    Color.Black
                } else {
                    Color.Transparent
                }

                TextButton(
                    border = BorderStroke(2.dp, SolidColor(color)),
                    onClick = { lineChartDataModel.pointDrawerType = Icons.Filled }
                ) {
                    Text(type.name)
                }
            }
        }
    }
}

@Composable
fun OffsetProgress(lineChartDataModel: LineChartDataModel) {
    Row(
        modifier = Modifier.padding(horizontal = horizontal),
        verticalAlignment = CenterVertically
    ) {
        Text("Offset")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
        ) {
            Slider(
                value = lineChartDataModel.horizontalOffset,
                onValueChange = { lineChartDataModel.horizontalOffset = it },
                valueRange = 0f.rangeTo(25f)
            )
        }
    }
}

@Composable
fun LineChartRow(lineChartDataModel: LineChartDataModel) {
    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        LineChart(
            lineChartData = lineChartDataModel.lineChartData,
            horizontalOffset = lineChartDataModel.horizontalOffset,
            pointDrawer = lineChartDataModel.pointDrawer
        )
    }
}

@Preview
@Composable
fun LineChartPreview() = LineChartScreen()