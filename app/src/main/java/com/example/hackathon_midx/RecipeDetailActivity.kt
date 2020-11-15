package com.example.hackathon_midx

import android.text.Html
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hackathon_midx.base_view.BaseActivity
import com.example.hackathon_midx.recipe.RecipeItemModel
import kotlinx.android.synthetic.main.activity_recipe_detail.*


class RecipeDetailActivity : BaseActivity() {
    var recipeItemModel: RecipeItemModel? = null
    override fun getLayoutRes(): Int = R.layout.activity_recipe_detail

    override fun addControls() {
        recipeItemModel = intent.getParcelableExtra(HomeFragment.DATA)

        txt_recipe_name.text = recipeItemModel?.mealName
        Glide.with(this@RecipeDetailActivity).load(recipeItemModel?.picture).into(img_main_top)
        txt_recipe_des.text =
            Html.fromHtml(
                recipeItemModel?.description?.replace("\\n", "")
            ).toString()

        initTutorialRow()
    }

    private fun initTutorialRow() {

        var countTutorialRow = 1
        recipeItemModel?.step?.forEach {

            val tutorialRow = layoutInflater.inflate(R.layout.layout_add_view_tutorial, null, false)
            val tutorialValue = tutorialRow.findViewById<TextView>(R.id.values)
            val tutorialTitle = tutorialRow.findViewById<TextView>(R.id.titles)
            val llAddImage = tutorialRow.findViewById<LinearLayout>(R.id.ll_add_image_tutorial)

            tutorialValue.text = it.content
            tutorialTitle.text = "$countTutorialRow."

            it.photos.forEach { photo ->

                val tutorialImage = ImageView(this)
                val layoutParams = LinearLayout.LayoutParams(300, 300)
                layoutParams.marginStart = 10
                tutorialImage.layoutParams = layoutParams

                Glide.with(this@RecipeDetailActivity).load(photo[1].url).into(tutorialImage)
                llAddImage.addView(tutorialImage)
            }

            countTutorialRow++
            ll_add_view_tutorial.addView(tutorialRow)
        }

        Log.d(
            "AAAA", "hello ${recipeItemModel?.ingredients}"
        )
        recipeItemModel?.ingredients?.forEach {
            val tutorialRow =
                layoutInflater.inflate(R.layout.layout_add_view_ingredients, null, false)
            val tutorialValue = tutorialRow.findViewById<TextView>(R.id.value)
            val tutorialTitle = tutorialRow.findViewById<TextView>(R.id.title)

            tutorialTitle.text = it.name
            tutorialValue.text = it.tranFormUnit()
            ll_add_view_ingredients.addView(tutorialRow)
        }
    }

    override fun addEvents() {

    }
}