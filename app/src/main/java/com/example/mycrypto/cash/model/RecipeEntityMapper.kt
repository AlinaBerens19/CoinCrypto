package com.example.coingechkoproject.cash.model

import com.example.coingechkoproject.domain.model.MarketData
import com.example.mycrypto.cash.util.EntityMapper


class MarketEntityMapper : EntityMapper<MarketEntity, MarketData> {

    override fun mapFromEntityModel(model: MarketEntity): MarketData {
        return MarketData(
            id = model.id,
            symbol = model.symbol,
            name = model.name,
            image = model.image,
            currentPrice = model.currentPrice,
            marketCap = model.marketCap,
            marketCapRank = model.marketCapRank,
            fully_diluted_valuation = model.fully_diluted_valuation,
            totalVolume = model.totalVolume,
            high24h = model.high24h,
            low24h = model.low24h,
            priceChange24h = model.priceChange24h,
            priceChangePercentage24h = model.priceChangePercentage24h,
            marketCapChange24h = model.marketCapChange24h,
            marketCapChangePercentage24h = model.marketCapChangePercentage24h,
            circulatingSupply = model.circulatingSupply,
            totalSupply = model.totalSupply,
            max_supply = model.max_supply,
            ath = model.ath,
            athChangePercentage = model.athChangePercentage,
            athDate = model.athDate,
            atl = model.atl,
            atl_change_percentage = model.atl_change_percentage,
//            roi = model.roi,
            lastUpdated = model.lastUpdated,
        )
    }


    override fun mapToEntityModel(domainModel: MarketData): MarketEntity {
        return MarketEntity(
            id = domainModel.id,
            symbol = domainModel.symbol,
            name = domainModel.name,
            image = domainModel.image,
            currentPrice = domainModel.currentPrice,
            marketCap = domainModel.marketCap,
            marketCapRank = domainModel.marketCapRank,
            fully_diluted_valuation = domainModel.fully_diluted_valuation,
            totalVolume = domainModel.totalVolume,
            high24h = domainModel.high24h,
            low24h = domainModel.low24h,
            priceChange24h = domainModel.priceChange24h,
            priceChangePercentage24h = domainModel.priceChangePercentage24h,
            marketCapChange24h = domainModel.marketCapChange24h,
            marketCapChangePercentage24h = domainModel.marketCapChangePercentage24h,
            circulatingSupply = domainModel.circulatingSupply,
            totalSupply = domainModel.totalSupply,
            max_supply = domainModel.max_supply,
            ath = domainModel.ath,
            athChangePercentage = domainModel.athChangePercentage,
            athDate = domainModel.athDate,
            atl = domainModel.atl,
            atl_change_percentage = domainModel.atl_change_percentage,
//            roi = domainModel.roi,
            lastUpdated = domainModel.lastUpdated,
        )
    }


    fun fromEntityList(initial: List<MarketEntity>): List<MarketData>{
        return initial.map { mapFromEntityModel(it) }
    }

    fun toEntityList(initial: List<MarketData>): List<MarketEntity>{
        return initial.map { mapToEntityModel(it) }
    }
}