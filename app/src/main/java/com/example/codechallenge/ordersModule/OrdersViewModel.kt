package com.example.codechallenge.ordersModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.common.entities.GetOrdersResponse
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.common.utis.GitHubService
import com.example.codechallenge.ordersModule.model.OrdersInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel : ViewModel() {
    private val price: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    private val orders: MutableLiveData<List<OrderEntitiy>> by lazy {
        MutableLiveData<List<OrderEntitiy>>().also {
            loadOrders()
        }
    }
    private val ordersInteractor: OrdersInteractor by lazy { OrdersInteractor() }


    fun getOrders(): LiveData<List<OrderEntitiy>> = orders
    fun getPrice(): LiveData<Double> = price

    fun loadOrders() {
        ordersInteractor.getOrders {
            orders.postValue( it)
            price.postValue(it.sumOf { it.calculatePrice() })
        }
    }
}