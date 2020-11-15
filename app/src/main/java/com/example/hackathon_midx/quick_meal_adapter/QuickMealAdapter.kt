package com.example.hackathon_midx.quick_meal_adapter

import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.LazyListAdapter
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import com.example.hackathon_midx.recipe.RecipeItemModel


class QuickMealAdapter(
    val callback: OnItemActionListener<RecipeItemModel, BaseItemAction>? = null
) : LazyListAdapter<RecipeItemModel>(callback) {

    override fun getHoldersMap() = super.getHoldersMap().apply {
        put(TYPE_ITEM, QuickMealViewHolder::class.java)
    }
}