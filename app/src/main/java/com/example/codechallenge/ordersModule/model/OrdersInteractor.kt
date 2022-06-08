package com.example.codechallenge.ordersModule.model

import com.example.codechallenge.common.entities.GetEuroPriceResponse
import com.example.codechallenge.common.entities.GetOrdersResponse
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.common.utis.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersInteractor {

    fun getOrders(ordersCallback: (List<OrderEntitiy>) -> Unit) {
             CoroutineScope(Dispatchers.IO).launch {
                try {
                    val result = GitHubService.create().getOrders()
                    ordersCallback(result.order!!)
                } catch (e: Exception) {
                    ordersCallback(mutableListOf())
                }
        }
    }
}