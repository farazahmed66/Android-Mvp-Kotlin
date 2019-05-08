package com.example.bigburger.ui.products

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bigburger.R
import com.example.bigburger.model.ProductModel
import com.example.bigburger.util.Utils

class ProductAdapter (private val context: Context, private val list: MutableList<ProductModel>, fragment: Fragment):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val listener : OnItemClickListener

    init {
        this.listener = fragment as OnItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  LayoutInflater.from(context).inflate(R.layout.card_view_product_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        list[position].qty = 1
        val product = list[position]
        holder.bind(product, context)
        holder.add.setOnClickListener {
            listener.itemDetail(product)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.product_name)!!
        val description = itemView.findViewById<TextView>(R.id.product_description)!!
        val price = itemView.findViewById<TextView>(R.id.product_price)!!
        val image = itemView.findViewById<ImageView>(R.id.product_image)!!
        val add = itemView.findViewById<ImageView>(R.id.btn_add)!!

        fun bind(item: ProductModel, context: Context) {
            name.text = item.title
            description.text = item.description

            //formatting price for user from utility class
            price.text = Utils.getPrice(item.price)
            Glide.with(context).load(item.thumbnail).placeholder(R.drawable.no_image).into(image)
        }
    }

    interface OnItemClickListener {
        fun itemDetail(productModel: ProductModel)
    }
}