package com.example.hackathon_midx.stock_adapter

import android.os.Parcelable
import com.example.hackathon_midx.base_adapter.IHolderItemModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockItemModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("picture")
    val iconUrl: String? = null,
    @Transient
    var isSelected: Boolean = false
) : IHolderItemModel, Parcelable {
    override fun getCompareId(): String = id ?: "0"
}