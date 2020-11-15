package com.example.hackathon_midx.recipe

import android.os.Parcelable
import com.example.hackathon_midx.base_adapter.IHolderItemModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeItemModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val mealName: String? = null,
    @SerializedName("chef_name")
    val chefName: String? = null,
    @SerializedName("time")
    val time: String? = null,
    @SerializedName("meal_image")
    val mealImage: String? = null,
    @SerializedName("recipe_star")
    val recipeStar: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("picture")
    val picture: String? = null,
    @SerializedName("steps")
    val step: ArrayList<RecipeStep>? = null,
    @SerializedName("ingredients")
    val ingredients: ArrayList<Ingredients>? = null,
    @SerializedName("category")
    val category: String? = null
) : IHolderItemModel, Parcelable {
    override fun getCompareId(): String = id ?: "0"
}

@Parcelize
data class RecipeStep(
    @SerializedName("photos")
    val photos: ArrayList<ArrayList<PhotoObject>>,
    @SerializedName("content")
    val content: String? = null
) : Parcelable

@Parcelize
data class PhotoObject(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: String? = null,
    @SerializedName("height")
    val height: String? = null
) : Parcelable

@Parcelize
data class Ingredients(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("amount")
    val amount: String? = null,
    @SerializedName("unit")
    val unit: String? = null
) : Parcelable {
    fun tranFormUnit(): String? {
        return "$amount $unit"
    }
}

