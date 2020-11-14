package com.example.hackathon_midx.recipe

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hackathon_midx.recipe.RecipeChildFragment


/**
 * Created by VTN on 11/15/2019.
 */
class RecipePagerAdapter(fm: FragmentManager, context: Context?) :
    FragmentPagerAdapter(fm) {
    private val mContext = context
    override fun getItem(p0: Int): Fragment {
        return RecipeChildFragment()
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Breakfast"
            1 -> "Lunch"
            2 -> "Dinner"
            3 -> "Popular"
            else -> null
        }
    }
}