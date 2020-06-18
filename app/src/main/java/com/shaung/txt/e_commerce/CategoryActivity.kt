package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.shaung.txt.e_commerce.adapters.CategoryAdapter
import com.shaung.txt.e_commerce.libby.H
import com.shaung.txt.e_commerce.libby.H.Companion.USER_TOKEN
import com.shaung.txt.e_commerce.libby.H.Companion.userAuth
import com.shaung.txt.e_commerce.modals.Category
import com.shaung.txt.e_commerce.services.ServiceBuilder
import com.shaung.txt.e_commerce.services.WebServices
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.setTitle("Categories")


        if (userAuth()){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        categoryRecycler.layoutManager = GridLayoutManager(this, 2)
        loadAllCats()
    }



    private fun loadAllCats(){
        val services : WebServices = ServiceBuilder.buildService(WebServices::class.java)
        val responseCats : Call<List<Category>> = services.getAllCat("Bearer $USER_TOKEN")

        responseCats.enqueue(object : Callback<List<Category>>{
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                H.L(t.message!!)
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.isSuccessful){
                    val cats : List<Category> = response.body()!!
                    H.L("There are ${cats.size}")
                    categoryRecycler.adapter = CategoryAdapter(this@CategoryActivity, cats)
                }
            }

        })
    }
}