
    package com.shaung.txt.e_commerce.services

    import com.shaung.txt.e_commerce.modals.Category
    import com.shaung.txt.e_commerce.modals.Token
    import retrofit2.Call
    import retrofit2.http.Field
    import retrofit2.http.FormUrlEncoded
    import retrofit2.http.GET
    import retrofit2.http.POST

    interface WebServices {
            @GET("cats")
            fun getAllCat() : Call<List<Category>>

            @FormUrlEncoded
            @POST("login")
            fun loginUser(@Field("email") email: String, @Field("password") password : String) : Call<Token>
}