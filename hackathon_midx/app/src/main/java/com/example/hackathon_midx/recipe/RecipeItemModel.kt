package com.example.hackathon_midx.recipe

import android.os.Parcelable
import com.example.hackathon_midx.base_adapter.IHolderItemModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeItemModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("meal_name")
    val mealName: String? = null,
    @SerializedName("chef_name")
    val chefName: String? = null,
    @SerializedName("time")
    val time: String? = null,
    @SerializedName("meal_image")
    val mealImage: String? = null,
    @SerializedName("recipe_star")
    val recipeStar: String? = null
) : IHolderItemModel, Parcelable {
    override fun getCompareId(): String = id ?: "0"
}