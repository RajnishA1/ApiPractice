package com.example.apipractice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apipractice.databinding.ActivityMainBinding
import com.example.apipractice.db.Product
import com.example.apipractice.retrofit.ApiInterface
import com.example.apipractice.retrofit.RetrofitInstance
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiService = RetrofitInstance.apiInterface
        val call: Call<Product> = apiService.getData()

        call.enqueue(object : Callback<Product?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
               if (response.isSuccessful){
                   val product:Product? = response.body()
                   Picasso.get().load(product?.thumbnail).into(binding.thumbnailImageView)
                   binding.apply {
                       tvTitle.text = product?.title
                       tvBrand.text = product?.brand
                       tvPrice.text = "Rs ${product?.price.toString()}"
                       tvDesc.text = product?.description
                       tvStock.text = product?.stock.toString()
                       rating.rating = product?.rating?.toFloat()!!
                   }

               }
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}