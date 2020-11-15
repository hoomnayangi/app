package com.example.hackathon_midx.stock_adapter

import android.widget.Filter
import android.widget.Filterable
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.LazyListAdapter
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import java.util.*


class StockAdapter(
    val callback: OnItemActionListener<StockItemModel, BaseItemAction>? = null
) : LazyListAdapter<StockItemModel>(callback), Filterable {

    private val mStringFilter: StringFilter = StringFilter()
    private var originalPairs: ArrayList<StockItemModel> = arrayListOf()

    fun initItems(itemList: ArrayList<StockItemModel>) {
        originalPairs = itemList
        updateItems(originalPairs)
        notifyDataSetChanged()
    }

    override fun getHoldersMap() = super.getHoldersMap().apply {
        put(TYPE_ITEM, StockViewHolder::class.java)
    }

    override fun getFilter(): Filter = mStringFilter

    inner class StringFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResults = FilterResults()
            val filterStrings = arrayListOf<StockItemModel>()
            if (constraint.isEmpty()) {
                filterResults.count = originalPairs.size
                filterResults.values = originalPairs
            } else {
                val constraintString = constraint.toString().toLowerCase(Locale.ROOT)
                originalPairs.forEach {
                    val ticker = it.name ?: ""
                    if (ticker.isNotEmpty() && (ticker.toLowerCase(Locale.ROOT)
                            .contains(constraintString))
                    ) {
                        filterStrings.add(it)
                    }
                }

                filterResults.count = filterStrings.size
                filterResults.values = filterStrings
            }
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            val resultList = results.values as? List<StockItemModel>
            if (!resultList.isNullOrEmpty()) {
                updateItems(resultList)
            }
        }
    }
}