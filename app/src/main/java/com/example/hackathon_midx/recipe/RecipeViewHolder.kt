package com.example.hackathon_midx.recipe

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.BaseViewHolder
import com.example.hackathon_midx.base_adapter.getHolderView
import kotlinx.android.synthetic.main.item_recipe.view.*


class RecipeViewHolder(parent: ViewGroup) :
    BaseViewHolder<RecipeItemModel>(parent.getHolderView(R.layout.item_recipe)) {

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
        with(itemView) {
            Glide.with(context).load(item.mealImage ?: "")
                .into(img_recipe)
            txt_chef_name.text = item.chefName ?: ""
            txt_recipe_star.text = item.recipeStar ?: ""
            txt_recipe_name.text = item.mealName ?: ""
        }
    }
}