package com.example.bigburger.model

data class Product (val ref: Int,
                    val title : String,
                    val description : String,
                    val thumbnail : String,
                    val price : Int,
                    var qty: Int)