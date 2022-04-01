package com.example.tp01.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tp01.domain.models.Delivery
import kotlinx.coroutines.flow.Flow


@Dao
interface DeliveryRepository {

    @Query("SELECT * FROM deliveries")
    fun retrieveAll(): Flow<List<Delivery>>

    @Insert
    suspend fun create(contacts: Delivery)

    @Query("DELETE FROM deliveries")
    suspend fun deleteAll()

}