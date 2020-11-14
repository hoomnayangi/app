package com.example.hackathon_midx.recipe

import com.example.hackathon_midx.MainActivity
import com.example.hackathon_midx.R
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import com.example.hackathon_midx.base_view.BaseActivity
import com.example.hackathon_midx.base_view.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe_child.*

class RecipeChildFragment : BaseFragment() {

    private var recipeAdapter: RecipeAdapter? = null
    override fun getLayoutRes(): Int = R.layout.fragment_recipe_child

    override fun addControls() {
        recipeAdapter =
            RecipeAdapter(object :
                OnItemActionListener<RecipeItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: RecipeItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {
                    (activity as? MainActivity)?.showSnackBar(
                        data.mealName,
                        BaseActivity.SNACKBAR_MSG_CODE_SUCCESS
                    )
                }
            })
        rv_recipe.adapter = recipeAdapter

        recipeAdapter?.updateItems(fakeQuickMeal())
    }

    override fun addEvents() {
    }

    private fun fakeQuickMeal(): List<RecipeItemModel> {
        val data = ArrayList<RecipeItemModel>()
        data.add(
            RecipeItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "10 min",
                "https://vn-test-11.slatic.net/shop/c8cae8bc74a10c5b890cb6a86071fe90.jpeg"
                , "4.5"
            )
        )
        data.add(
            RecipeItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "15 min",
                "https://vn-test-11.slatic.net/shop/cae8f3e353fb78860b9b3a0f351ea15c.jpeg"
                , "4.8"
            )
        )
        data.add(
            RecipeItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "20 min",
                "https://vn-test-11.slatic.net/shop/453429526fb7078e28912a0c55b1dea3.png"
                , "4.2"
            )
        )
        data.add(
            RecipeItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "25 min",
                "https://vn-test-11.slatic.net/shop/1336818f3312c8a075820cfd457a2cbe.jpeg"
                , "4.1"
            )
        )
        data.add(
            RecipeItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "30 min",
                "https://vn-test-11.slatic.net/shop/cae8f3e353fb78860b9b3a0f351ea15c.jpeg"
                , "3.6"
            )
        )

        return data
    }
}