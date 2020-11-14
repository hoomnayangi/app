package com.example.hackathon_midx.stock_adapter

import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.LazyListAdapter
import com.example.hackathon_midx.base_adapter.OnItemActionListener


class StockAdapter(
    val callback: OnItemActionListener<StockItemModel, BaseItemAction>? = null
) : LazyListAdapter<StockItemModel>(callback) {

    override fun getHoldersMap() = super.getHoldersMap().apply {
        put(TYPE_ITEM, StockViewHolder::class.java)
    }
}