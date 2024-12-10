package com.example.assessment.network

import com.example.assessment.model.Item
import retrofit2.http.GET
import retrofit2.http.Headers


interface GeneralApi {
    companion object {
        const val HEADER = ""
        const val HEADER_KEY = ""
        const val BASE_URL = "https://www.google.com.hk/"
    }

    @Headers("$HEADER:$HEADER_KEY")
    @GET("v1/stock/txn")
    suspend fun getItems(): List<Item>


}