package com.example.mycrypto.cash.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chart_data")
data class ChartEntity

    (
    // Value from API
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    // Value from API
    @ColumnInfo(name = "timestamp")
    var timestamp: Long,

    // Value from API
    @ColumnInfo(name = "price")
    var price: Double

)