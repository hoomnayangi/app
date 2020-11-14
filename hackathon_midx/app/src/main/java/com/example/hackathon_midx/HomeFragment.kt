package com.example.hackathon_midx

import android.util.Log
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import com.example.hackathon_midx.base_view.BaseActivity.Companion.SNACKBAR_MSG_CODE_SUCCESS
import com.example.hackathon_midx.base_view.BaseFragment
import com.example.hackathon_midx.quick_meal_adapter.QuickMealAdapter
import com.example.hackathon_midx.quick_meal_adapter.QuickMealItemModel
import com.example.hackathon_midx.stock_adapter.StockAdapter
import com.example.hackathon_midx.stock_adapter.StockItemModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private var mStockAdapter: StockAdapter? = null
    private var mQuickMealAdapter: QuickMealAdapter? = null
    private var stockList: List<StockItemModel> = fakeStockData()

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun addControls() {
        mStockAdapter =
            StockAdapter(object :
                OnItemActionListener<StockItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: StockItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {
                    stockList[position].isSelected = !stockList[position].isSelected

                    Log.d("AAAA", stockList.toString())

//                    mStockAdapter?.updateItems(stockList)
                   mStockAdapter?.notifyItemChanged(position)

//                    (activity as? MainActivity)?.showSnackBar(data.name, SNACKBAR_MSG_CODE_SUCCESS)
                }
            })
        rv_list_stock.adapter = mStockAdapter

        mStockAdapter?.updateItems(stockList)

        mQuickMealAdapter =
            QuickMealAdapter(object :
                OnItemActionListener<QuickMealItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: QuickMealItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {
                    (activity as? MainActivity)?.showSnackBar(
                        data.mealName,
                        SNACKBAR_MSG_CODE_SUCCESS
                    )
                }
            })
        rv_list_quick_meal.adapter = mQuickMealAdapter

        mQuickMealAdapter?.updateItems(fakeQuickMeal())
    }

    private fun fakeQuickMeal(): List<QuickMealItemModel> {
        val data = ArrayList<QuickMealItemModel>()
        data.add(
            QuickMealItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "10 min",
                "https://vn-test-11.slatic.net/shop/c8cae8bc74a10c5b890cb6a86071fe90.jpeg"
            )
        )
        data.add(
            QuickMealItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "15 min",
                "https://vn-test-11.slatic.net/shop/cae8f3e353fb78860b9b3a0f351ea15c.jpeg"
            )
        )
        data.add(
            QuickMealItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "20 min",
                "https://vn-test-11.slatic.net/shop/453429526fb7078e28912a0c55b1dea3.png"
            )
        )
        data.add(
            QuickMealItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "25 min",
                "https://vn-test-11.slatic.net/shop/1336818f3312c8a075820cfd457a2cbe.jpeg"
            )
        )
        data.add(
            QuickMealItemModel(
                "1",
                "Banana",
                "Ambramhin",
                "30 min",
                "https://vn-test-11.slatic.net/shop/cae8f3e353fb78860b9b3a0f351ea15c.jpeg"
            )
        )

        return data
    }

    private fun fakeStockData(): List<StockItemModel> {
        val data = ArrayList<StockItemModel>()
        data.add(
            StockItemModel(
                "1",
                "Banana",
                "https://img.icons8.com/emoji/48/000000/banana-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "2",
                "Mango",
                "https://img.icons8.com/emoji/48/000000/mango-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "3",
                "Watermelon",
                "https://img.icons8.com/emoji/48/000000/watermelon-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "4",
                "Cappuccino",
                "https://img.icons8.com/emoji/48/000000/mango-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "5",
                "Macchiato",
                "https://img.icons8.com/emoji/48/000000/banana-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "6",
                "IceCoffee",
                "https://img.icons8.com/emoji/48/000000/watermelon-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "7",
                "Espresso",
                "https://img.icons8.com/emoji/48/000000/banana-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "8",
                "Mocha",
                "https://img.icons8.com/emoji/48/000000/mango-emoji.png"
            )
        )
        data.add(
            StockItemModel(
                "9",
                "Latte",
                "https://img.icons8.com/emoji/48/000000/watermelon-emoji.png"
            )
        )
        return data
    }

    override fun addEvents() {
    }
}