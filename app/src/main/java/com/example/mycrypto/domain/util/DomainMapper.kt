package com.example.coingechkoproject.domain.util

import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.coingechkoproject.network.model.CoinMarketsDto


interface DomainMapper <T, DomainModel>{

    fun mapToDomainModel(model: CoinMarketsDto): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}