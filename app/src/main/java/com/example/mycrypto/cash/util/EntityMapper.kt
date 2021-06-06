package com.example.mycrypto.cash.util

import com.example.coingechkoproject.cash.model.MarketEntity


interface EntityMapper <T, DomainModel>{

    fun mapFromEntityModel(model: MarketEntity): DomainModel

    fun mapToEntityModel(domainModel: DomainModel): T
}