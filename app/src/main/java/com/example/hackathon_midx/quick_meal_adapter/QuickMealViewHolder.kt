package com.example.hackathon_midx.quick_meal_adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.BaseViewHolder
import com.example.hackathon_midx.base_adapter.getHolderView
import com.example.hackathon_midx.recipe.RecipeItemModel
import kotlinx.android.synthetic.main.item_quick_meal.view.*


class QuickMealViewHolder(parent: ViewGroup) :
    BaseViewHolder<RecipeItemModel>(parent.getHolderView(R.layout.item_quick_meal)) {

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

    override fun bindData(item: RecipeItemModel) {
        super.bindData(item)
        with(itemView) {x
            Glide.with(context).load(item.picture ?: "")
                .apply(RequestOptions().placeholder(R.drawable.rice))
                .into(img_meal)
//            txt_chef_name.text = item.chefName ?: ""
            txt_meal_name.text = item.mealName ?: ""
//            txt_waiting_time.text = item.time ?: ""
        }
    }
}