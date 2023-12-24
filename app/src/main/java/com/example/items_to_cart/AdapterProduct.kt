package com.example.items_to_cart

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterProduct(val products: List<Product>) :
    RecyclerView.Adapter<AdapterProduct.ProductViewHolder>() {


    interface OnAddToCartClickListener{
        fun onAddToCartClick(position: Int)
    }


    private var addToCartClickListener:OnAddToCartClickListener? = null


    fun setOnAddToCartClickListener(listener:OnAddToCartClickListener) {
        this.addToCartClickListener = listener
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var productTitle: TextView = itemView.findViewById(R.id.tv_product_title)
        var productPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        var productImage: ImageView = itemView.findViewById(R.id.iv_product)
        var addToCart: TextView = itemView.findViewById(R.id.ic_add_to_cart)


        init {
            addToCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    addToCartClickListener?.onAddToCartClick(position)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]
        holder.productTitle.text = product.productName
        holder.productPrice.text = product.productPrice
        holder.productImage.setImageResource(product.productImage)
    }


    override fun getItemCount(): Int {
        return products.size
    }
}