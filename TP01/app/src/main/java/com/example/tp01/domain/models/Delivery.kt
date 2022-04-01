package com.example.tp01.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deliveries")
data class Delivery(
    @ColumnInfo(name="iaspyx") val iaspyx: Float = 0.0F,
    @ColumnInfo(name="smiathil") val smiathil: Float = 0.0F,
    @ColumnInfo(name="jasmalt") val jasmalt: Float = 0.0F,
    @ColumnInfo(name="vethyx") val vethyx: Float = 0.0F,
    @ColumnInfo(name="blierium") val blierium: Float = 0.0F
) {
    @PrimaryKey(autoGenerate = true) var idDelivery: Int = 0
}