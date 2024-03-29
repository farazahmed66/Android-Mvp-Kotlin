package com.example.bigburger.model

data class ProductModel (val ref: Int,
                         val title : String,
                         val description : String,
                         val thumbnail : String,
                         var price : Int,
                         var qty: Int)