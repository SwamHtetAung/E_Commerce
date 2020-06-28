package com.shaung.txt.e_commerce.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shaung.txt.e_commerce.R
import com.shaung.txt.e_commerce.SingleProductActivity
import com.shaung.txt.e_commerce.modals.CartProduct
import com.shaung.txt.e_commerce.modals.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class CartAdapter (val context : Context, val products : List<CartProduct>) : RecyclerView.Adapter<CartAdapter.pViewHolder>() {

    class pViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pViewHolder {
        return pViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: pViewHolder, position: Int) {
        val product = products[position]
        holder.itemView.tvTitle.text = product.name
        Picasso.get().load(product.image).into(holder.itemView.tvImage)
        holder.itemView.tvPrice.text = product.price.toString()
        holder.itemView.btnDetail.setOnClickListener {
            Toast.makeText(context, "You bought ${product.count} items of this kind.", Toast.LENGTH_SHORT).show()
        }
    }

}


