
    package com.shaung.txt.e_commerce.services

    import com.shaung.txt.e_commerce.modals.CartProduct
    import com.shaung.txt.e_commerce.modals.Category
    import com.shaung.txt.e_commerce.modals.Products
    import com.shaung.txt.e_commerce.modals.Token
    import retrofit2.Call
    import retrofit2.http.*

    interface WebServices {
        @GET("cats")
        fun getAllCat(@Header("Authorization") token : String) : Call<List<Category>>


        @FormUrlEncoded
        @POST("login")
        fun loginUser(@Field("email") email : String,
                      @Field("password") password : String
        ):Call<Token>

        @GET("product/cat/{id}")
        fun getProductsOfACategory(@Header("Authorization") token : String,
                                    @Path("id")id : Int) : Call<Products>

        @FormUrlEncoded
        @POST("previewCart")
        fun getCartPreviewItems (@Header("Authorization") token : String,
                                 @Field("items")items : String) : Call<List<CartProduct>>


}