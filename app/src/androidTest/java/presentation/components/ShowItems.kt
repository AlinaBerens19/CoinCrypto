package com.example.coingechkoproject.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.coil.rememberCoilPainter



@Composable
fun HomeCard(c: Context, url: String, name: String, price: String) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(
                        bottom = 9.dp,
                        top = 9.dp,
                        start = 5.dp,
                        end = 5.dp
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 16.dp
            ) {

                Image(
                    painter = rememberCoilPainter(
                        request = url
                    ),
                    contentDescription = "Android Logo",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(Modifier.height(16.dp))

            Text(
                text = name,
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = (25.sp)
                ),
                color = MaterialTheme.colors.primary,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                price,
                modifier = Modifier.padding(8.dp),
                style = typography.h6,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

