package com.example.mycrypto.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.coingechkoproject.domain.model.MarketData
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun CoinCard(
    marketData: MarketData,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberCoilPainter(
                request = marketData.image,
            ),
            contentDescription = "",
            Modifier.size(45.dp)
        )
            Spacer(
                Modifier
                    .width(10.dp)
                    .align(Alignment.CenterVertically))

            Text(marketData.id, style = MaterialTheme.typography.h6)

            Spacer(
                Modifier
                    .width(10.dp)
                    .align(Alignment.CenterVertically))

            Text("${marketData.currentPrice} USD", style = MaterialTheme.typography.subtitle2)
        }
    }
}
