package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.shaung.txt.e_commerce.modals.Category
import com.shaung.txt.e_commerce.modals.Token
import com.shaung.txt.e_commerce.services.ServiceBuilder
import com.shaung.txt.e_commerce.services.WebServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object{
        var USER_TOKEN : String? = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLoginAds.isSelected = true

        btnLogin.setOnClickListener {
            val email = etLoginEmail.toString()
            val password = etLoginPassword.toString()
//            Toast.makeText(this, "\n$email  \n$password", Toast.LENGTH_LONG).show()

            loginAUser(email, password)
        }

        btnLoginCancel.setOnClickListener {
            etLoginEmail.text = null
            etLoginPassword.text = null
        }

        etTextViewToRegister.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
        getAllCategories()
    }

    private fun loginAUser(email : String, pass : String){
        val services : WebServices = ServiceBuilder.buildService(WebServices::class.java)
        val responseLogin : Call<Token> = services.loginUser(email, pass)

        responseLogin.enqueue(object : Callback<Token>{
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("my_message", t.message)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                var token : Token = response.body()!!
                Log.d("my_message", token.token)
            }

        })
    }


    private fun getAllCategories(){
        val service : WebServices = ServiceBuilder.buildService(WebServices::class.java)
        val responseCategory : Call<List<Category>> = service.getAllCat()

        responseCategory.enqueue(object : Callback<List<Category>>{
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d("my_message", t.message)
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful){
                    val cats : List<Category> = response.body()!!
                    Log.d("my_message", cats.toString())
                }else{
                    Log.d("my_message", "Response Fail")
                }
            }
        })
    }


}