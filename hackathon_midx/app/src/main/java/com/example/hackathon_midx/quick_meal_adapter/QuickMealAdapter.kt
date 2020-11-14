package com.example.hackathon_midx.quick_meal_adapter

import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.LazyListAdapter
import com.example.hackathon_midx.base_adapter.OnItemActionListener


class QuickMealAdapter(
    val callback: OnItemActionListener<QuickMealItemModel, BaseItemAction>? = null
) : LazyListAdapter<QuickMealItemModel>(callback) {

    override fun getHoldersMap() = super.getHoldersMap().apply {
        put(TYPE_ITEM, QuickMealViewHolder::class.java)
    }
}