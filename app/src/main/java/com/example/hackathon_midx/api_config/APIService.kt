package com.example.hackathon_midx.api_config

import com.example.hackathon_midx.recipe.RecipeResponse
import com.example.hackathon_midx.stock_adapter.StockResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("recipe")
    fun getRecipeList(@Query("ingredients") ingredients: String): Call<RecipeResponse>

    @GET("ingredient")
    fun getStocksList(@Query("key") key: String): Call<StockResponse>

    @GET("recipe/highlight")
    fun getRecipeHighlightList(
        @Query("lat") lat: String,
        @Query("long") long: String
    ): Call<RecipeResponse>
}