package com.example.bigburger.ui.cart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bigburger.R
import com.example.bigburger.model.Product
import com.example.bigburger.util.Utils

class CartAdapter  (private val context: Context, private val list: MutableList<Product>):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  LayoutInflater.from(context).inflate(R.layout.card_view_cart, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]
        holder.bind(product, context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.product_name)!!
        val price = itemView.findViewById<TextView>(R.id.product_price)!!
        val image = itemView.findViewById<ImageView>(R.id.product_image)!!
        var qty = itemView.findViewById<TextView>(R.id.qty)

        fun bind(item: Product, context: Context) {
            val qtyText = "QTY : "+item.qty.toString()
            name.text = item.title
            qty.text = qtyText
            price.text = Utils.getPrice(item.price)
            Glide.with(context).load(item.thumbnail).placeholder(R.drawable.no_image).into(image)
        }
    }

}