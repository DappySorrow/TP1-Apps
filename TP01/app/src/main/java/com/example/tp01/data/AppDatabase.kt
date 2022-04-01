package com.example.tp01.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp01.data.repositories.DeliveryRepository
import com.example.tp01.domain.models.Delivery

@Database(entities = [Delivery::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deliveryRepository(): DeliveryRepository

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context):
                AppDatabase = Instance ?: synchronized(this) {
            //Création de la base de données
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "consortium_database"
            ).build()

            Instance = instance
            return instance
        }
    }
}