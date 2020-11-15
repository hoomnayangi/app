package com.example.hackathon_midx.recipe

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.hackathon_midx.R
import com.example.hackathon_midx.recipe.RecipeChildFragment


/**
 * Created by VTN on 11/15/2019.
 */
class RecipePagerAdapter(fm: FragmentManager, context: Context?) :
    FragmentPagerAdapter(fm) {
    private val mContext = context
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> RecipeChildFragment()
            1 -> RecipeLunchFragment()
            2 -> RecipeDinnerFragment()
            else -> RecipeChildFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext?.getString(R.string.breakfast)
            1 -> mContext?.getString(R.string.lunch)
            2 -> mContext?.getString(R.string.dinner)
            else -> null
        }
    }
}