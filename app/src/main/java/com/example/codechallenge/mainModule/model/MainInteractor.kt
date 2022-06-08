package com.example.codechallenge.mainModule.model

import com.example.codechallenge.common.entities.GetEuroPriceResponse
import com.example.codechallenge.common.utis.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainInteractor {
    fun getEuroPrice(callback: (GetEuroPriceResponse) -> Unit) {
       CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = GitHubService.create().getEuroPrices()
                callback(result)
            } catch (e: Exception) {
                callback(GetEuroPriceResponse())
            }
        }
    }
}