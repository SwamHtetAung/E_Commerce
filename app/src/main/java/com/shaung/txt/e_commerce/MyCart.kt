package com.shaung.txt.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaung.txt.e_commerce.adapters.CartAdapter
import com.shaung.txt.e_commerce.libby.H
import com.shaung.txt.e_commerce.libby.H.Companion.USER_TOKEN
import com.shaung.txt.e_commerce.modals.CartProduct
import com.shaung.txt.e_commerce.services.ServiceBuilder
import com.shaung.txt.e_commerce.services.WebServices
import kotlinx.android.synthetic.main.activity_my_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCart : AppCompatActivity() {
    var cartCount : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        supportActionBar?.setTitle("My Cart's Item")

        val cartKeys = H.getAllKeys()
        H.L("$cartKeys")

        getCartItems(cartKeys)
        cartRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getCartItems (cartKeys : String){
        val services = ServiceBuilder.buildService(WebServices::class.java)
        val responseCartProducts = services.getCartPreviewItems("Bearer $USER_TOKEN", cartKeys)

        responseCartProducts.enqueue(object: Callback<List<CartProduct>>{
            override fun onFailure(call: Call<List<CartProduct>>, t: Throwable) {
                H.L(t.message!!)
            }

            override fun onResponse(call: Call<List<CartProduct>>, response: Response<List<CartProduct>>){
                if (response.isSuccessful){
                    val products = response.body()!!
                    cartRecycler.adapter = CartAdapter(this@MyCart, products)
                }else{
                    H.L("Something went wrong!")
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.cart)
        MenuItemCompat.setActionView(item, R.layout.my_cart_layout)

        val cartView = MenuItemCompat.getActionView(item)
        cartCount = cartView?.findViewById(R.id.cartCount)

        cartCount!!.text = H.getCartCount().toString()
        return super.onCreateOptionsMenu(menu)
    }
}