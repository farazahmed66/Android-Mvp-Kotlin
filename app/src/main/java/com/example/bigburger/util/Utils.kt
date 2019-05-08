package com.example.bigburger.util

import com.example.bigburger.model.CartModel
import com.example.bigburger.model.ProductModel

// Utility Class used to store data which is reusable

class Utils {
    companion object {
        val cartList = arrayListOf<CartModel>()
        val productList = arrayListOf<ProductModel>()

        fun getPrice(price : Int): String {
            var newPrice = price.toFloat()
            newPrice /= 100
            var price = String.format("%.2f", newPrice)
            price = "$price ₺"
            return price
        }

        fun getItemTotal(ref: Int, qty: Int): Int {
            var price = 0
            var totalP = 0
            productList.forEachIndexed { index, element ->
                if (element.ref == ref) {
                    price = productList[index].price
                    totalP = price*qty
                }
            }
            return totalP
        }

        fun getTotal(): String {
            var totalPrice = 0
            for (item in cartList) {
                val price: Int = item.price
                totalPrice += price
            }
            var price = totalPrice.toFloat()
            price /= 100
            val price1 = String.format("%.2f", price)
            return price1
        }
    }
}