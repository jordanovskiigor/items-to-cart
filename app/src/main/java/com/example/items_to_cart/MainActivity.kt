package com.example.items_to_cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_products)
        val layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager
        val products = createProduct()
        val adapter = AdapterProduct(products)
        recyclerView.adapter = adapter
    }

    private fun createProduct():List<Product> {
        val productList = mutableListOf<Product>()
        productList.add(Product("Mushroom","4.99$",R.drawable.pizza1))
        productList.add(Product("Pepperoni"," 3.99$",R.drawable.pizza2))
        productList.add(Product("Four Cheese"," 3.99$",R.drawable.pizza3))
        productList.add(Product("White"," 3.99$",R.drawable.pizza4))
        productList.add(Product("Vegetarian"," 3.99$",R.drawable.pizza5))
        productList.add(Product("Margherita"," 3.99$",R.drawable.pizza6))
        productList.add(Product("Vegetarian"," 3.99$",R.drawable.pizza5))
        productList.add(Product("Margherita"," 3.99$",R.drawable.pizza6))
        return productList
    }
}