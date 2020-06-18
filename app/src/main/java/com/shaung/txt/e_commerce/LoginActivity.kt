package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shaung.txt.e_commerce.libby.H
import com.shaung.txt.e_commerce.libby.H.Companion.L
import com.shaung.txt.e_commerce.libby.H.Companion.USER_TOKEN
import com.shaung.txt.e_commerce.modals.Category
import com.shaung.txt.e_commerce.modals.Token
import com.shaung.txt.e_commerce.services.ServiceBuilder
import com.shaung.txt.e_commerce.services.WebServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLoginAds.isSelected = true

        btnLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val password = etLoginPassword.text.toString()
//            Toast.makeText(this, "\n$email  \n$password", Toast.LENGTH_LONG).show()

            loginAUser(email, password)

        }

        btnLoginCancel.setOnClickListener {
            etLoginEmail.text = null
            etLoginPassword.text = null
        }

        etTextViewToRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun loginAUser(email : String, pass : String){
        val services : WebServices = ServiceBuilder.buildService(WebServices::class.java)
        val responseLogin : Call<Token> = services.loginUser(email, pass)

        responseLogin.enqueue(object : Callback<Token>{
            override fun onFailure(call: Call<Token>, t: Throwable) {
                L(t.message!!)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                val token : Token = response.body()!!
                USER_TOKEN = token.token
                L(token.token)

                val intent = Intent(this@LoginActivity, CategoryActivity::class.java)
                startActivity(intent)
            }

        })

    }

}