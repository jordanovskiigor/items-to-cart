package com.example.items_to_cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterProduct.OnAddToCartClickListener {

    private lateinit var cartBadge: TextView
    private var badgeCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_products)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        cartBadge = findViewById(R.id.cart_badge)
        updateCartBadge()
        val products = createProduct()
        val adapter = AdapterProduct(products)

        adapter.setOnAddToCartClickListener(this)
        recyclerView.adapter = adapter
        recyclerView.adapter = adapter


    }

    private fun createProduct(): List<Product> {
        val productList = mutableListOf<Product>()
        productList.add(Product("Mushroom", "4.99$", R.drawable.pizza1))
        productList.add(Product("Pepperoni", " 3.99$", R.drawable.pizza2))
        productList.add(Product("Four Cheese", " 3.99$", R.drawable.pizza3))
        productList.add(Product("White", " 3.99$", R.drawable.pizza4))
        productList.add(Product("Vegetarian", " 3.99$", R.drawable.pizza5))
        productList.add(Product("Margherita", " 3.99$", R.drawable.pizza6))
        productList.add(Product("Vegetarian", " 3.99$", R.drawable.pizza5))
        productList.add(Product("Margherita", " 3.99$", R.drawable.pizza6))
        return productList
    }

    override fun onAddToCartClick(position: Int) {
        badgeCount++
        updateCartBadge()
        val clickedProduct = createProduct()[position]
        Toast.makeText(this, "${clickedProduct.productName} added to cart", Toast.LENGTH_SHORT)
            .show()
    }

    private fun updateCartBadge() {
        if (badgeCount > 0) {
            cartBadge.text = badgeCount.toString()
            cartBadge.visibility = View.VISIBLE
        } else {
            cartBadge.visibility = View.GONE
        }
    }

}