package com.example.coingechkoproject.di

import com.example.coingechkoproject.cash.MarketDao
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.coingechkoproject.cash.model.MarketEntityMapper
import com.example.coingechkoproject.interactors.market_data.SearchMarketData
import com.example.coingechkoproject.network.NetworkService
import com.example.coingechkoproject.domain.CoinDtoMapper
import com.example.mycrypto.interactors.market_data.GetAllMarketsData
import com.example.mycrypto.interactors.market_data.SearchCryptoById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideSearchChartData(
        networkService: NetworkService,
        dtoMapper: CoinDtoMapper,
        marketDao: MarketDao,
        marketEntityMapper: MarketEntityMapper
    ): SearchMarketData
    {
        return SearchMarketData(
            networkService = networkService,
            dtoMapper = dtoMapper,
            marketDao = marketDao,
            marketEntityMapper = marketEntityMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideSearchMarketsByID(
        networkService: NetworkService,
        dtoMapper: CoinDtoMapper,
        marketDao: MarketDao,
        marketEntityMapper: MarketEntityMapper
    ): SearchCryptoById
    {
        return SearchCryptoById(
            networkService = networkService,
            dtoMapper = dtoMapper,
            marketDao = marketDao,
            marketEntityMapper = marketEntityMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetAllMarketsData(
        networkService: NetworkService,
        dtoMapper: CoinDtoMapper,
        marketDao: MarketDao,
        marketEntityMapper: MarketEntityMapper
    ): GetAllMarketsData
    {
        return GetAllMarketsData(
            networkService = networkService,
            dtoMapper = dtoMapper,
            marketDao = marketDao,
            marketEntityMapper = marketEntityMapper
        )
    }

}