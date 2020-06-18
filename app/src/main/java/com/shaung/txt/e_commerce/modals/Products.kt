package com.shaung.txt.e_commerce.modals

import com.google.gson.annotations.SerializedName

class Products (
    val current_page : Int,
    @SerializedName("data")val products : List<Product>
)