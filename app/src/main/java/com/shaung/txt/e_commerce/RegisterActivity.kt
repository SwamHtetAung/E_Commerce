package com.shaung.txt.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setTitle("RegisterActivity")

        btnRegister.setOnClickListener {
            val username = regUserName.text
            val email = regEmail.text
            val password = regPassword.text
            Toast.makeText(this, "\n$username \n$email \n$password", Toast.LENGTH_LONG).show()
        }

        btnRegisterCancel.setOnClickListener {
            regUserName.text = null
            regEmail.text = null
            regPassword.text = null
        }

        regTextViewToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}