package com.example.hackathon_midx

import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.hackathon_midx.api_config.APIService
import com.example.hackathon_midx.api_config.APIUtils
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import com.example.hackathon_midx.base_view.BaseActivity.Companion.SNACKBAR_MSG_CODE_SUCCESS
import com.example.hackathon_midx.base_view.BaseFragment
import com.example.hackathon_midx.quick_meal_adapter.QuickMealAdapter
import com.example.hackathon_midx.quick_meal_adapter.QuickMealItemModel
import com.example.hackathon_midx.recipe.RecipeFragment
import com.example.hackathon_midx.recipe.RecipeItemModel
import com.example.hackathon_midx.recipe.RecipeResponse
import com.example.hackathon_midx.stock_adapter.StockAdapter
import com.example.hackathon_midx.stock_adapter.StockItemModel
import com.example.hackathon_midx.stock_adapter.StockResponse
import kotlinx.android.synthetic.main.action_bar_mid_x.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class HomeFragment : BaseFragment() {

    companion object {
        val DATA = "DATA"
    }

    private var apiService: APIService? = null
    private var mStockAdapter: StockAdapter? = null
    private var mQuickMealAdapter: QuickMealAdapter? = null
    private var stockList: List<StockItemModel> = arrayListOf()
    private var mQueryString: String? = null
    private var ingredients: ArrayList<String> = arrayListOf("1")
    private var rvSkeletonRcvStock: RecyclerViewSkeletonScreen? = null
    private var rvSkeletonRcvQuick: RecyclerViewSkeletonScreen? = null
    private var firstOpen = true


    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun addControls() {=
        txt_action_bar_title.text = getString(R.string.search)
        apiService = APIUtils().getServer()
        callAPIStock("") // Call Default Stock List
        callAPIRecipe()
    }

    private fun callAPIRecipe() {
        apiService?.getRecipeList(TextUtils.join(",", ingredients))
            ?.enqueue(object : Callback<RecipeResponse> {
                override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                    Timber.d(t)
                }

                override fun onResponse(
                    call: Call<RecipeResponse>,
                    response: Response<RecipeResponse>
                ) {
                    response.body()?.listRecipes?.let {
                        rvSkeletonRcvQuick?.hide()
                        mQuickMealAdapter?.updateItems(it)
                        if (firstOpen) {
                            firstOpen = false
                            ingredients.clear()
                        }
                    }

                }

            })
    }

    fun callAPIStock(key: String) {
        apiService?.getStocksList(key)?.enqueue(object : Callback<StockResponse> {
            override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                Timber.d(t)
            }

            override fun onResponse(
                call: Call<StockResponse>,
                response: Response<StockResponse>
            ) {
                response.body()?.listStocks?.let { stockLists ->
                    rvSkeletonRcvStock?.hide()
                    if (stockLists.isNullOrEmpty()) {
                        vl_stock.displayedChild = 1
                        return
                    }
                    vl_stock.displayedChild = 0

                    ingredients.forEach { ingredientsValue ->
                        stockLists.forEachIndexed { stockListsIndex, stockItemModel ->
                            if (ingredientsValue == stockItemModel.id) {
                                stockLists[stockListsIndex].isSelected =
                                    !stockLists[stockListsIndex].isSelected
                            }
                        }
                    }
                    stockList = stockLists
                    mStockAdapter?.updateItems(stockLists)
                }
            }

        })
    }

    override fun addEvents() {

        search_view_home.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                search_view_home.clearFocus()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                mQueryString = s

                (requireActivity() as? MainActivity)?.initLooper()?.let {
                    Handler(it)
                        .postDelayed({
                            callAPIStock(mQueryString ?: "")
                        }, 200)
                }

                return true
            }
        })

        search_view_home.setOnClickListener {
            search_view_home.isIconified = false
        }

        mStockAdapter =
            StockAdapter(object :
                OnItemActionListener<StockItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: StockItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {
                    stockList[position].isSelected = !stockList[position].isSelected

                    if (stockList[position].isSelected) {
                        ingredients.add("${data.id}")
                    } else {
                        ingredients.remove("${data.id}")
                    }

                    callAPIRecipe()
                    mStockAdapter?.notifyDataSetChanged()

                }
            })

        rv_list_stock.adapter = mStockAdapter

        rvSkeletonRcvStock = Skeleton.bind(rv_list_stock)
            .adapter(mStockAdapter)
            .load(R.layout.view_skeleton_stock)
            .duration(2000)
            .show()

        mQuickMealAdapter =
            QuickMealAdapter(object :
                OnItemActionListener<RecipeItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: RecipeItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {

                    startActivity(Intent(requireContext(), RecipeDetailActivity::class.java).apply {
                        putExtra(DATA, data)
                    })
                }
            })
        rv_list_quick_meal.adapter = mQuickMealAdapter

        rvSkeletonRcvQuick = Skeleton.bind(rv_list_quick_meal)
            .adapter(mQuickMealAdapter)
            .load(R.layout.view_skeleton_stock)
            .duration(2000)
            .show()

        ll_next.setOnClickListener {
            activity?.let {
                (it as? MainActivity)?.openFragment(
                    R.id.main_container,
                    RecipeFragment(),
                    RecipeFragment().getName() ?: "RecipeFragment"
                )
            }
        }
    }

}