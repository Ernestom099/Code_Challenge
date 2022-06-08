package com.example.codechallenge.common.utis

class EuroPrice private constructor(
     val usd: Double,
    private val gbp: Double,
    private val aud: Double
) {
    fun toDollars(price: Double) = price * usd
    fun toPound(price: Double) = price * gbp
    fun toAustralianDollar(price: Double) = price * aud

    companion object {
        private var instance: EuroPrice? = null

        operator fun invoke(
            usd: Double = 0.0,
            gbp: Double = 0.0,
            aud: Double = 0.0
        ) = synchronized(this) {
            if (instance == null) {
                instance = EuroPrice(usd, gbp, aud)
            }
            instance
        }
    }
}