package com.example.hackathon_midx.base_adapter

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback<T : IHolderItemModel>(
    private val old: List<T>,
    private val new: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].getCompareId() == new[newIndex].getCompareId()
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}