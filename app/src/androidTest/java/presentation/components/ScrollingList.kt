package com.example.coingechkoproject.presentation.components

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coingechkoproject.domain.model.MarketData
import com.google.accompanist.coil.rememberCoilPainter




@Composable
fun ImageListItem(coin: Int, url: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberCoilPainter(
                request = url
                //"https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$coin", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList(url: String?) {
    val listSize = 30
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {

            LazyColumn(state = scrollState) {
                items(listSize) {
                    ImageListItem(it, url)
                }
            }
        }
    }
}
