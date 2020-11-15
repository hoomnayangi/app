package com.example.hackathon_midx.stock_adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.BaseViewHolder
import com.example.hackathon_midx.base_adapter.getHolderView
import kotlinx.android.synthetic.main.item_stock.view.*

class StockViewHolder(parent: ViewGroup) :
    BaseViewHolder<StockItemModel>(parent.getHolderView(R.layout.item_stock)) {

    init {
        itemView.setOnClickListener {
            mItem?.let { it1 ->
                mActionListener?.onItemAction(
                    it1,
                    BaseItemAction.ON_ITEM_ROW_CLICK,
                    adapterPosition
                )
            }
        }
    }

    override fun bindData(item: StockItemModel) {
        super.bindData(item)
        with(itemView) {
            Glide.with(context).load(item.iconUrl ?: "").apply(RequestOptions().apply {
                placeholder(R.drawable.ic_banana)
            }).into(img_stock)
            txt_stock_name.text = item.name ?: ""

            if (item.isSelected) {
                rl_checked.visibility = View.VISIBLE
            } else {
                rl_checked.visibility = View.GONE
            }
        }
    }
}