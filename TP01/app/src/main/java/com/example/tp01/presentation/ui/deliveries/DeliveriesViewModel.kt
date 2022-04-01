package com.example.tp01.presentation.ui.deliveries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tp01.data.AppDatabase
import com.example.tp01.domain.models.Delivery
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DeliveriesViewModel(application: Application) : AndroidViewModel(application) {

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

}