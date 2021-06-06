package com.example.mycrypto.presentation.ui.coinScreen



sealed class CryptoDetailStateEvent{

    data class GetMarketEvent(
        val id: String
    ): CryptoDetailStateEvent()

}