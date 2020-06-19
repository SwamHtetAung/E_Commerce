package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MenuItemCompat
import com.shaung.txt.e_commerce.modals.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_product.*
import kotlinx.android.synthetic.main.my_cart_layout.*

class SingleProductActivity : AppCompatActivity() {
    var cartCount : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_product)

        val product = intent.getParcelableExtra<Product>("product")

        supportActionBar?.setTitle(product.name)
        Picasso.get().load(product.image).into(productImage)
        productPrice.text = product.price.toString()
        productDescription.text = product.description.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.cart)
        MenuItemCompat.setActionView(item, R.layout.my_cart_layout)

        val cartView = MenuItemCompat.getActionView(item)
        cartCount = cartView?.findViewById(R.id.cartCount)
        val cartImage : ImageView? = cartView?.findViewById(R.id.cartImage)

        cartImage?.setOnClickListener {
            startActivity(Intent(this@SingleProductActivity, MyCart::class.java))
        }
        return super.onCreateOptionsMenu(menu)
    }
}