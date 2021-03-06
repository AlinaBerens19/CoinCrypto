package com.example.coingechkoproject.presentation.components



import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.mycrypto.R


@Composable
fun HomeCard(
    marketEntity: MarketEntity,
    onClick:()-> Unit,
){
    Card(
        modifier = Modifier.padding(
            bottom = 9.dp,
            top = 9.dp,
            start = 5.dp,
            end = 5.dp
        )
            .fillMaxWidth()
            .clickable(onClick = onClick)
        ,
        shape =  RoundedCornerShape(19.dp),
        elevation = 16.dp,

        ) {
        Row (
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ){
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                elevation = 19.dp,
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Image(
                    painter = painterResource(R.drawable.dogecoin_doge_price_and_reviews),
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop,
                )

            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                marketEntity!!.name?.let {
                    Text(text = it,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = (22.sp)
                        ),
                        color = MaterialTheme.colors.primary
                    )
                }
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = marketEntity.currentPrice.toString(),
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            end = 25.dp
                        )
                    )
                }
            }
        }
    }
}