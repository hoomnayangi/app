package com.example.hackathon_midx.recipe

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("data")
    val listRecipes: ArrayList<RecipeItemModel>? = null
)