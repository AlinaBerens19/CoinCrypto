package com.example.coingechkoproject.cash

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coingechkoproject.cash.model.MarketEntity
import com.example.mycrypto.cash.model.ChartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Markets(coins: List<MarketEntity>): LongArray

    @Query("SELECT * FROM market_data")
    abstract fun getFlowMarkets(): Flow<List<MarketEntity>?>

    @Query("SELECT * FROM market_data")
    suspend fun getAllMarkets(): List<MarketEntity>

    @Query("SELECT * FROM market_data WHERE id = :id")
    suspend fun getMarketsById(id: String?): MarketEntity

    @Query("DELETE FROM market_data WHERE id IN (:ids)")
    suspend fun deleteMarkets(ids: List<Int>): Int

    @Query("DELETE FROM market_data")
    suspend fun deleteAllMarkets()

    @Query("DELETE FROM market_data WHERE id = :primaryKey")
    suspend fun deleteMarkets(primaryKey: Int): Int

    //from pricey to cheap
    @Query("""
        SELECT * FROM market_data               
        ORDER BY currentPrice DESC
        """)
    suspend fun getMarketsDescLimit(): List<MarketEntity>

    //from pricey to cheap
    @Query("""
        SELECT * FROM market_data               
        ORDER BY currentPrice ASC
        """)
    suspend fun getMarketsAscLimit(): List<MarketEntity>


//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertCharData(charts: List<ChartEntity>): LongArray
//
//    @Query("SELECT * FROM chart_data")
//    suspend fun getChartData(): List<ChartEntity>

}