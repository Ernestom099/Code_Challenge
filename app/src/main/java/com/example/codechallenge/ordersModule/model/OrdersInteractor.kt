package com.example.codechallenge.ordersModule.model

import com.example.codechallenge.common.entities.GetOrdersResponse
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.common.utis.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersInteractor {

    fun getOrders(ordersCallback: (List<OrderEntitiy>) -> Unit) {
        val call = GitHubService.create().getOrders()
        call?.enqueue(object : Callback<GetOrdersResponse> {
            override fun onResponse(
                call: Call<GetOrdersResponse>,
                response: Response<GetOrdersResponse>
            ) {
                if (response.body() != null && response.body()!!.order != null) {
                    ordersCallback(response.body()!!.order!!)
                } else {
                    ordersCallback(mutableListOf())
                }
            }

            override fun onFailure(call: Call<GetOrdersResponse>, t: Throwable) {
                ordersCallback(mutableListOf())
            }
        })
    }
}