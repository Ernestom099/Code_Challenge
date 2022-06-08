package com.example.codechallenge.common.entities

import com.example.codechallenge.common.utis.EuroPrice
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
    val length: Int?
) : Serializable {

    fun calculatePrice(): Double {
        val price = when (type) {
            TYPE_PIZZA -> {
                calculatePizzaPrice()
            }
            TYPE_SALAD -> {
                calculateSaladPrice()
            }
            TYPE_BREAD -> {
                calculateBreadPrice()
            }
            else -> {
                calculateSausagePrice()
            }
        }

        EuroPrice.invoke()?.let {
            return it.toDollars(price)
        }
        return  0.0
    }

    fun calculateDollarsPrice(): Double {
        EuroPrice.invoke()?.let {
            return it.toDollars(calculatePrice())
        }
        return  calculatePrice()
    }

    private fun calculatePizzaPrice(): Double {
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


    private fun calculateSaladPrice(): Double {
        var price = 0.0
        ingredients?.let {
            price = price.plus(2)
            price = price.plus((ingredients.size - 2) * 0.5)

        }
        return price
    }

    private fun calculateSausagePrice(): Double =
        if (length == SMALL_SAUSAGE_LENGTH) SMALL_SAUSAGE_PRICE else LONG_SAUSAGE_PRICE

    private fun calculateBreadPrice(): Double = BREAD_PRICE


    companion object {

        const val SMALL_SAUSAGE_PRICE = 1.5
        const val LONG_SAUSAGE_PRICE = 3.0

        const val BREAD_PRICE = 3.0

        const val SMALL_SAUSAGE_LENGTH = 5

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
        const val TYPE_BREAD = "bread"
    }
}
