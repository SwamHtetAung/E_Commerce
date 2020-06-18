package com.shaung.txt.e_commerce.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaung.txt.e_commerce.R
import com.shaung.txt.e_commerce.SingleCategoryProducts
import com.shaung.txt.e_commerce.modals.Category
import kotlinx.android.synthetic.main.category_row.view.*

class CategoryAdapter(val context : Context, val cats : List<Category>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cat = cats[position]
        holder.itemView.categoryName.text = cat.name


        holder.itemView.categoryName.setOnClickListener {
            val intent = Intent(context, SingleCategoryProducts::class.java)
            intent.putExtra("cat_id", cat.id.toString())
            intent.putExtra("cat_name", cat.name)
            context.startActivity(intent)
        }
    }
}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)