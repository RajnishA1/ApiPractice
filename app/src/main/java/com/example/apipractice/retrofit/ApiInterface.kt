package com.example.apipractice.retrofit

import com.example.apipractice.db.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products/2")
    fun getData() : Call<Product>

}