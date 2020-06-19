package com.shaung.txt.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        supportActionBar?.setTitle("My Cart")
    }
}