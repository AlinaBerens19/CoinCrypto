package com.example.coingechkoproject.presentation.components



import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coingechkoproject.domain.model.MarketData

import com.google.accompanist.coil.rememberCoilPainter



@Composable
fun HomeCard(
    coin: String,
    url: String,
    price: Double,
    onClick: () -> Unit,
){
    Card(
        modifier = Modifier.padding(
            bottom = 9.dp,
            top = 9.dp,
            start = 5.dp,
            end = 5.dp
        )
            .fillMaxWidth()
            .clickable(onClick = { onClick() })
        ,
        shape =  RoundedCornerShape(19.dp),
        elevation = 16.dp,

        ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = rememberCoilPainter(
                    request = url,
                ),
                contentDescription = "",
                Modifier.size(45.dp)
            )
            Spacer(
                Modifier
                    .width(10.dp)
                    .align(Alignment.CenterVertically))

            Text(coin, style = MaterialTheme.typography.h6)

            Spacer(
                Modifier
                    .width(10.dp)
                    .align(Alignment.CenterVertically))

            Text("$price USD", style = MaterialTheme.typography.subtitle2)

        }
    }
}