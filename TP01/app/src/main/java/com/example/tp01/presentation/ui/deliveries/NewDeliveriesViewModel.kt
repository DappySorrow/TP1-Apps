package com.example.tp01.presentation.ui.deliveries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tp01.data.AppDatabase
import com.example.tp01.data.repositories.TraderRepository
import com.example.tp01.domain.models.Delivery
import com.example.tp01.domain.models.Trader
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class NewDeliveriesViewModel(application: Application) : AndroidViewModel(application) {

    private val traderRepository = TraderRepository(application)
    private val _trader = MutableLiveData<Trader>()
    val trader: LiveData<Trader> get() = _trader

    //Aller chercher les données à l'ouverture
    init {
        viewModelScope.launch {
            traderRepository.trader.collect {
                _trader.value = it
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private val deliveryRepository = AppDatabase.getDatabase(application).deliveryRepository()
    private val _deliveries = MutableLiveData<List<Delivery>>()

    val deliveries: LiveData<List<Delivery>> get() = _deliveries

    init {
        viewModelScope.launch {
            deliveryRepository.retrieveAll().collect {
                _deliveries.value = it
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //Retirer des élément choisi des sliders
    fun removeElements(iaspyx: Float, smiathil: Float, jasmalt: Float, vethyx: Float, bilerium: Float) {
        _trader.value!!.iaspyx -= iaspyx
        if (_trader.value!!.iaspyx < 0.01F){
            _trader.value!!.iaspyx = 0.01F
        }

        _trader.value!!.smiathil -= smiathil
        if (_trader.value!!.smiathil < 0.01F){
            _trader.value!!.smiathil = 0.01F
        }

        _trader.value!!.jasmalt -= jasmalt
        if (_trader.value!!.jasmalt < 0.01F){
            _trader.value!!.jasmalt = 0.01F
        }

        _trader.value!!.vethyx -= vethyx
        if (_trader.value!!.vethyx < 0.01F){
            _trader.value!!.vethyx = 0.01F
        }

        _trader.value!!.bilerium -= bilerium
        if (_trader.value!!.bilerium < 0.01F){
            _trader.value!!.bilerium = 0.01F
        }

    }

    //Faire une sauvegarde de Trader
    fun save(){
        viewModelScope.launch {
            traderRepository.saveRepo(_trader.value!!.name, _trader.value!!.iaspyx, _trader.value!!.smiathil, _trader.value!!.jasmalt,
                _trader.value!!.vethyx, _trader.value!!.bilerium)
        }
    }

    //Ajouter un Trader au RecyclerView
    fun addOne(delivery:Delivery){
        viewModelScope.launch {
            deliveryRepository.create(delivery)
        }
    }
}