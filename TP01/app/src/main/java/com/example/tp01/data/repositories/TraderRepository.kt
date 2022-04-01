package com.example.tp01.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tp01.domain.models.Delivery
import com.example.tp01.domain.models.Trader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("Consortium")

class TraderRepository(private val context: Context) {

    //L'équivalent d'un Dictionaire, mais au lieu de la mémoire, c'est dans un fichier
    object PreferencesKeys {
        val NAME = stringPreferencesKey("name")
        val IASPYX = floatPreferencesKey("iaspyx")
        val SMIATHIL = floatPreferencesKey("smiathil")
        val JASMALT = floatPreferencesKey("jasmalt")
        val VETHYX = floatPreferencesKey("vethyx")
        val BLIERIUM = floatPreferencesKey("blierium")
    }

    val trader: Flow<Trader> = context.dataStore.data.map { preferences ->
        val name = preferences[PreferencesKeys.NAME] ?: ""
        val iaspyx = preferences[PreferencesKeys.IASPYX] ?: 200.0F
        val smiathil = preferences[PreferencesKeys.SMIATHIL] ?: 200.0F
        val jasmalt = preferences[PreferencesKeys.JASMALT] ?: 200.0F
        val vethyx = preferences[PreferencesKeys.VETHYX] ?: 200.0F
        val blierium = preferences[PreferencesKeys.BLIERIUM] ?: 200.0F

        Trader(name, iaspyx, smiathil, jasmalt, vethyx, blierium)
    }

    //A cause du context.dataStore.edit, la fonction doit être  suspend
    suspend fun saveRepo(name: String, iaspyx: Float, smiathil: Float, jasmalt: Float, vethyx: Float, blierium: Float) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.NAME] = name
            preferences[PreferencesKeys.IASPYX] = iaspyx
            preferences[PreferencesKeys.SMIATHIL] = smiathil
            preferences[PreferencesKeys.JASMALT] = jasmalt
            preferences[PreferencesKeys.VETHYX] = vethyx
            preferences[PreferencesKeys.BLIERIUM] = blierium
        }
    }
}