package com.example.hackathon_midx.recipe

import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.LazyListAdapter
import com.example.hackathon_midx.base_adapter.OnItemActionListener


class RecipeAdapter(
    val callback: OnItemActionListener<RecipeItemModel, BaseItemAction>? = null
) : LazyListAdapter<RecipeItemModel>(callback) {

    override fun getHoldersMap() = super.getHoldersMap().apply {
        put(TYPE_ITEM, RecipeViewHolder::class.java)
    }
}