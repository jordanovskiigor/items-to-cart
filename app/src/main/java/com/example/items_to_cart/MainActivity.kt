package com.example.items_to_cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterProduct.OnAddToCartClickListener {

    private lateinit var cartBadge: TextView
    private lateinit var recyclerView:RecyclerView
    private var badgeCount: Int = 0
    companion object {
        val cartProducts = mutableListOf<Product>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_products)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        cartBadge = findViewById(R.id.cart_badge)
        val products = createProduct()
        val adapter = AdapterProduct(products)

        adapter.setOnAddToCartClickListener(this)
        recyclerView.adapter = adapter
        recyclerView.adapter = adapter

        val cartIcon = findViewById<ImageView>(R.id.ic_cart_icon)
        cartIcon.setOnClickListener {
            showCartDialog()
        }
    }


    private fun createProduct(): List<Product> {
        val productList = mutableListOf<Product>()
        productList.add(Product("Mushroom", "4.99", R.drawable.pizza1))
        productList.add(Product("Pepperoni", " 3.99", R.drawable.pizza2))
        productList.add(Product("Four Cheese", " 3.99", R.drawable.pizza3))
        productList.add(Product("White", " 3.99", R.drawable.pizza4))
        productList.add(Product("Vegetarian", " 3.99", R.drawable.pizza5))
        productList.add(Product("Margherita", " 3.99", R.drawable.pizza6))
        return productList
    }

    override fun onAddToCartClick(position: Int) {
        val clickedProduct = createProduct()[position]
        val existingProduct = cartProducts.find { it.productName == clickedProduct.productName }

        if (existingProduct != null) {

            existingProduct.quantity++
            existingProduct.totalPrice += clickedProduct.productPrice.toDouble()
        } else {
            clickedProduct.quantity = 1
            clickedProduct.totalPrice = clickedProduct.productPrice.toDouble()
            cartProducts.add(clickedProduct)
        }

        badgeCount++
        updateCartBadge()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun updateCartBadge() {
        val cartSize = cartProducts.size
        if (cartSize > 0) {
            cartBadge.text = badgeCount.toString()
            cartBadge.visibility = View.VISIBLE
        } else {
            cartBadge.visibility = View.GONE
        }
    }

    private fun showCartDialog() {
        val cartItems = getCartItems()
        val dialog = AlertDialog.Builder(this)
            .setTitle("Your cart")
            .setPositiveButton("Close", null)
            .setItems(cartItems.toTypedArray(), null)
            .create()
        dialog.show()
    }

    private fun getCartItems(): List<String> {
        val cartItems = mutableListOf<String>()
        for (product in cartProducts) {
            cartItems.add("${product.productName}\t\t${product.quantity}\t\t${product.totalPrice}$")
        }
        return cartItems
    }
}