package com.example.hackathon_midx

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.hackathon_midx.base_view.BaseActivity
import com.example.hackathon_midx.recipe.RecipeFragment
import kotlinx.android.synthetic.main.action_bar_mid_x.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun getLayoutRes(): Int = R.layout.activity_main
    override fun addControls() {


        val homeFragment = HomeFragment()
        openFragment(
            R.id.main_container,
            homeFragment,
            homeFragment.getName() ?: "HomeFragment"
        )

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        main_bottom_navigation.isBehaviorTranslationEnabled = false

        val homeTab =
            AHBottomNavigationItem(getString(R.string.home), R.drawable.ic_baseline_home_24, R.color.dandelion)
        val recipeTab = AHBottomNavigationItem(
            getString(R.string.recipes),
            R.drawable.ic_chef_hat,
            R.color.dandelion
        )

        main_bottom_navigation.apply {
            addItem(homeTab)
            addItem(recipeTab)
            defaultBackgroundColor = getColorMidX(this@MainActivity, R.color.white)
            inactiveColor = getColorMidX(this@MainActivity, R.color.silver_chalice)
            accentColor = getColorMidX(this@MainActivity, R.color.black)
            titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
            currentItem = 0
        }

    }

    override fun addEvents() {
        main_bottom_navigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                when (position) {
                    0 -> {
                        val homeFragment = HomeFragment()
                        openFragment(
                            R.id.main_container,
                            homeFragment,
                            homeFragment.getName() ?: "HomeFragment"
                        )
                    }
                    1 -> {
                        val recipeFragment =
                            RecipeFragment()
                        openFragment(
                            R.id.main_container,
                            recipeFragment,
                            recipeFragment.getName() ?: "RecipeFragment"
                        )
                    }

                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
            finish()
            return
        }
        doubleBackToExitPressedOnce = true
        showSnackBar(resources.getString(R.string.click_back_again_to_exit))

        initLooper()?.let {
            Handler(it).postDelayed({ doubleBackToExitPressedOnce = false }, 2000L)
        }

    }

}