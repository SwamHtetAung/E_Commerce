package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaung.txt.e_commerce.adapters.ProductAdapter
import com.shaung.txt.e_commerce.libby.H
import com.shaung.txt.e_commerce.libby.H.Companion.L
import com.shaung.txt.e_commerce.libby.H.Companion.USER_TOKEN
import com.shaung.txt.e_commerce.modals.Products
import com.shaung.txt.e_commerce.services.ServiceBuilder
import com.shaung.txt.e_commerce.services.WebServices
import kotlinx.android.synthetic.main.activity_single_category_products.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SingleCategoryProducts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_category_products)

        if (H.userAuth()){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        val bundle : Bundle = intent.extras!!
        val catId : String  = bundle.getString("cat_id")!!
        var catName = bundle.get("cat_name")
        supportActionBar?.setTitle("Categories > $catName")
//        toast("$catId")
        singleCategoryProductRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadAllProductsOfACategory(catId)

    }

    private fun loadAllProductsOfACategory(catId : String){
        val services : WebServices = ServiceBuilder.buildService(WebServices::class.java)
        val responseProduct = services.getProductsOfACategory("Bearer $USER_TOKEN" ,catId.toInt())

        responseProduct.enqueue(object : Callback<Products>{
            override fun onFailure(call: Call<Products>, t: Throwable) {
                H.L(t.message!!)
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful){
                    val prod : Products = response.body()!!
                    val products = prod.products
                    L("There are ${products.size} products here.")
                    singleCategoryProductRecycler.adapter = ProductAdapter(this@SingleCategoryProducts, products)
                }else{
                    H.L("Something went wrong!")
                }

            }

        })
    }
}