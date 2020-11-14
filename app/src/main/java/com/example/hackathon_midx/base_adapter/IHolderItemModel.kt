package com.example.hackathon_midx.base_adapter

import com.example.hackathon_midx.base_adapter.LazyListAdapter.Companion.TYPE_ITEM

interface IHolderItemModel {
    fun getCompareId(): String
    fun getHolderType(): Int = TYPE_ITEM
    fun isItemSelected(): Boolean = false
    fun setItemSelected(isSelect: Boolean) {
        // override by subclass
    }
}