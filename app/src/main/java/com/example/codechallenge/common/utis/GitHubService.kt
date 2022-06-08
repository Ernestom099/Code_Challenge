package com.example.codechallenge.common.utis

import com.example.codechallenge.common.entities.GetOrdersResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GitHubService {
    @GET("r-casarez-garcia-charter/57260b09dcbf415cef0f4fbe91ab468b/raw/7b27a6846259c6bd2b59d691f6e43c195e4e621d/order.json")
    fun getOrders(): Call<GetOrdersResponse>
    companion object {

        private const val BASE_URL = "https://gist.githubusercontent.com/"

        fun create() : GitHubService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(GitHubService::class.java)

        }
    }
}