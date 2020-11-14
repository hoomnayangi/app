package com.example.hackathon_midx.base_adapter

/**
 *
 * @param ItemModel item model
 * @param Action : BaseItemAction
 */
interface OnItemActionListener<ItemModel, Action : IBaseItemAction> {
    fun onItemAction(data: ItemModel, action: Action, position: Int)
}