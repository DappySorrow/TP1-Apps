package com.example.tp01.presentation.ui.splash

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.example.tp01.data.AppDatabase
import com.example.tp01.data.repositories.TraderRepository
import com.example.tp01.domain.models.Delivery
import com.example.tp01.domain.models.Trader
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    //Donner accèes aux données de traderRepository
    private val traderRepository = TraderRepository(application)
    private val _trader = MutableLiveData<Trader>()
    val trader: LiveData<Trader> get() = _trader

    //Initier la collect d'information
    init {
        viewModelScope.launch {
            traderRepository.trader.collect {
                _trader.value = it
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //Donner accèes aux données de deliveryRepository
    private val deliveryRepository = AppDatabase.getDatabase(application).deliveryRepository()
    private val _deliveries = MutableLiveData<List<Delivery>>()
    val deliveries: LiveData<List<Delivery>> get() = _deliveries

    //Initier la collect d'information
    init {
        viewModelScope.launch {
            deliveryRepository.retrieveAll().collect {
                _deliveries.value = it
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    fun load() {
        val min = 50.0
        val max = 200.0

        _trader.value!!.smiathil += Random.nextDouble(min, max).toFloat()
        _trader.value!!.iaspyx += Random.nextDouble(min, max).toFloat()
        _trader.value!!.jasmalt += Random.nextDouble(min, max).toFloat()
        _trader.value!!.vethyx += Random.nextDouble(min, max).toFloat()
        _trader.value!!.bilerium += Random.nextDouble(min, max).toFloat()

    }

    fun save(nomCommercant: String) {
        viewModelScope.launch {
            traderRepository.saveRepo(
                nomCommercant,
                _trader.value!!.iaspyx,
                _trader.value!!.smiathil,
                _trader.value!!.jasmalt,
                _trader.value!!.vethyx,
                _trader.value!!.bilerium
            )
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            deliveryRepository.deleteAll()
        }
    }

}