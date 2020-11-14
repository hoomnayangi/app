package com.example.hackathon_midx.base_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> : RecyclerView.ViewHolder {
    var mActionListener: OnItemActionListener<T, IBaseItemAction>? = null
    var mItem: T? = null

    constructor(itemView: View) : super(itemView)

    constructor(parent: ViewGroup) : super(Space(parent.context))

    open fun bindData(item: T) {
        mItem = item
    }
}

/**
 * create holder item view
 */
fun ViewGroup.getHolderView(@LayoutRes layoutItemRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutItemRes, this, false)
}

fun RecyclerView.ViewHolder.getContext(): Context {
    return itemView.context
}