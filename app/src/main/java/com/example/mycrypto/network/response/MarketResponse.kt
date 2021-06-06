package com.example.coingechkoproject.network.response

import com.example.coingechkoproject.network.data.MarketChart
import com.example.coingechkoproject.network.model.CoinMarketsDto


data class MarketResponse(

    var market: List<CoinMarketsDto>,
)