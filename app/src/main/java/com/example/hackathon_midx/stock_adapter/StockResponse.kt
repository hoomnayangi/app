package com.example.hackathon_midx.stock_adapter

import com.google.gson.annotations.SerializedName

data class StockResponse(
    @SerializedName("data")
    val listStocks: ArrayList<StockItemModel>? = null
)