package com.example.codechallenge.mainModule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.common.utis.EuroPrice
import com.example.codechallenge.mainModule.model.MainInteractor

class MainViewModel : ViewModel() {

    private val euroPrice: MutableLiveData<EuroPrice> by lazy {
        MutableLiveData<EuroPrice>().also {
            loadEuroPrice()
        }
    }

    private val mainInteractor by lazy {
        MainInteractor()
    }

    fun getEuroPrices(): MutableLiveData<EuroPrice> = euroPrice


    fun loadEuroPrice() {
        mainInteractor.getEuroPrice {
            with(it) {
                euroPrice.postValue(EuroPrice.invoke(USD, GBP, AUD))
            }
        }
    }
}