package com.example.hackathon_midx.recipe

import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.hackathon_midx.BaseFragmentInteraction
import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_view.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : BaseFragment() {

    private var recipePagerAdapter: RecipePagerAdapter? = null

    override fun getLayoutRes(): Int =
        R.layout.fragment_recipe

    override fun addControls() {
        recipePagerAdapter =
            RecipePagerAdapter(
                childFragmentManager,
                context
            )
        viewPager_recipe.adapter = recipePagerAdapter
        viewPager_recipe.offscreenPageLimit = 3;
        tab_layout_recipe.setupWithViewPager(viewPager_recipe)
    }

    override fun addEvents() {
        viewPager_recipe.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val fragmentItem = recipePagerAdapter?.getItem(position)
                (fragmentItem as BaseFragmentInteraction).updateFragmentData(position)
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }
}