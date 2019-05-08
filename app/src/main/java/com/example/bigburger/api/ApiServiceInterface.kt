package com.example.bigburger.api

import com.example.bigburger.model.ProductModel
import com.example.bigburger.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("dump/mobiletest1.json")
    fun getProductList() : Observable<List<ProductModel>>

    companion object Factory{
        fun create() : ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return  retrofit.create(ApiServiceInterface::class.java)
        }
    }
}