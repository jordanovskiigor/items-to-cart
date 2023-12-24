package com.example.items_to_cart

import android.media.Image
import android.widget.ImageView
import android.widget.TextView

data class Product(
    val productName: String,
    val productPrice: String,
    val productImage: Int,
    var quantity:Int = 0,
    var totalPrice:Double = 0.0
)


