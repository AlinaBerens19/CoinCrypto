package com.example.coingechkoproject.cash.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coingechkoproject.cash.MarketDao
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.mycrypto.cash.model.ChartEntity


@Database(entities = [MarketEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun marketDao(): MarketDao

    companion object{
        val DATABASE_NAME: String = "market_db"
    }

}