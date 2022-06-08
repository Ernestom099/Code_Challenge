package com.example.codechallenge.common.entities

import java.io.Serializable


data class OrderEntitiy(
    val type: String,
    val name: String?,
    val ingredients: List<String>?,
    val size: String?,
    val toppings: List<String>?,
    val sauce: List<String>?,
    val shape: String?,
    val grain: String?,
    val meat: String?,
    val length: Long?
) : Serializable {

    fun calculatePizzaPrice(): Double {
        var price = 0.0
        when (size) {
            PIZZA_BIG_SIZE -> {
                price = price.plus(PIZZA_BIG_PRICE)
            }
            PIZZA_MEDIUM_SIZE -> {

                price = price.plus(PIZZA_MEDIUM_PRICE)
            }
            PIZZA_SMALL_SIZE -> {
                price = price.plus(PIZZA_SMALL_PRICE)
            }
        }

        price = price.plus((toppings?.size ?: 0.0).toDouble() * TOPPING_PRICE)
        price = price.plus((sauce?.size ?: 0.0).toDouble() * SAUCE_PRICE)





        return price
    }


    fun calculateSaladPrice(): Double {
        var price = 0.0

        ingredients?.let {
            price = price.plus(2)
            price = price.plus((ingredients.size - 2) * 0.5)

        }


        return price
    }

    companion object {
        const val PIZZA_SMALL_PRICE = 5.0
        const val PIZZA_MEDIUM_PRICE = 10.0
        const val PIZZA_BIG_PRICE = 15.0

        const val TOPPING_PRICE = 1
        const val SAUCE_PRICE = 5

        const val PIZZA_SMALL_SIZE = "small"
        const val PIZZA_MEDIUM_SIZE = "medium"
        const val PIZZA_BIG_SIZE = "big"

        const val TYPE_PIZZA = "pizza"
        const val TYPE_SALAD = "salad"
    }
}
