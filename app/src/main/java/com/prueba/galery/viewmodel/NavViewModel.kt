package com.prueba.galery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NavViewModel @Inject constructor() : ViewModel() {

    private val newDestination: MutableLiveData<Int> = MutableLiveData()

    fun getDestination(): LiveData<Int>? {
        return newDestination
    }

    fun setNewDestination(destinationId: Int) {
        newDestination.value = destinationId
    }

}