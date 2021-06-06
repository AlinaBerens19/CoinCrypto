package com.example.mycrypto

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Screen metadata for Rally.
 */
//sealed class Screen(
//    val route: String,
//){
//    object DetailCoin: Screen("detailCoin")
//
//    object MarketData: Screen("marketData")
//}

enum class Screen(
) {
    DetailCoin(

    ),
    MarketData(

    );

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                MarketData.name -> MarketData
                DetailCoin.name -> DetailCoin
                null -> MarketData
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}