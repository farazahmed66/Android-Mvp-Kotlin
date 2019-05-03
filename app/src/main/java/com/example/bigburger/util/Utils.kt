package com.example.bigburger.util

import com.example.bigburger.model.Product

// Utility Class used to store data which is reusable

class Utils {
    companion object {
        val cartList = arrayListOf<Product>()

        fun getPrice(price : Int): String {
            var newPrice = price.toFloat()
            newPrice = newPrice/100
            var price = String.format("%.2f", newPrice)
            price = "$price ₺"
            return price
        }

        fun getTotal(): String {
            var totalPrice = 0
            for (item in cartList) {
                val price: Int = item.price
                totalPrice += price
            }
            var price = totalPrice.toFloat()
            price = price/100
            var price1 = String.format("%.2f", price)
            return price1
        }
    }
}