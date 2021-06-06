package com.example.coingechkoproject.di

import com.example.coingechkoproject.network.NetworkService
import com.example.coingechkoproject.domain.CoinDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCoinMapper(): CoinDtoMapper
    {
        return CoinDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): NetworkService {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkService::class.java)
    }

}