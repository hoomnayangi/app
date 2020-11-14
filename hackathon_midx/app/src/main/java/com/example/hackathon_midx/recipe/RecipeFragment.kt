package com.example.hackathon_midx.recipe

import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_view.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : BaseFragment() {

    override fun getLayoutRes(): Int =
        R.layout.fragment_recipe

    override fun addControls() {
        val recipePagerAdapter =
            RecipePagerAdapter(
                childFragmentManager,
                context
            )
        viewPager_recipe.adapter = recipePagerAdapter
        tab_layout_recipe.setupWithViewPager(viewPager_recipe)
    }

    override fun addEvents() {
    }
}