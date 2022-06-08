package com.example.codechallenge.ordersModule

import com.example.codechallenge.common.entities.OrderEntitiy

interface OnClickListener {
    fun onClick(order: OrderEntitiy)
}