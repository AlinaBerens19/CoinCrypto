package com.example.coingechkoproject.di

import androidx.room.Room

import com.example.coingechkoproject.cash.MarketDao
import com.example.coingechkoproject.cash.database.AppDatabase
import com.example.coingechkoproject.cash.model.MarketEntityMapper
import com.example.mycrypto.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CashModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase): MarketDao{
        return db.marketDao()
    }

    @Singleton
    @Provides
    fun provideCashRecipeMapper(): MarketEntityMapper
    {
        return MarketEntityMapper()
    }
}