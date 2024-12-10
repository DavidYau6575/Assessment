package com.example.assessment.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("buy_sell")
    val buySell: String,
    @SerializedName("order_type")
    val orderType: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("stock_symbol")
    val stockSymbol: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("transaction_id")
    val transactionId: String
)