package com.example.hackathon_midx.base_adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class LazyListAdapter<ItemModel : IHolderItemModel>(
    var actionListener: OnItemActionListener<ItemModel, out IBaseItemAction>? = null
) : RecyclerView.Adapter<BaseViewHolder<ItemModel>>() {

    companion object {
        const val TYPE_LOADING = -1
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
        const val TYPE_EMPTY = 2
    }

    private var mHolderTypeArray: SparseArray<Class<out BaseViewHolder<*>>>
    var mItems: ArrayList<ItemModel> = arrayListOf()
    var mSelectedItemPosition = 0

    init {
        mHolderTypeArray = getHoldersMap()
    }

    open fun getHoldersMap() = SparseArray<Class<out BaseViewHolder<*>>>()

    override fun getItemCount() = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemModel> {
        val holder = createHolder(parent, viewType)
        holder.mActionListener =
            actionListener as? OnItemActionListener<ItemModel, IBaseItemAction>?
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemModel>, position: Int) {
        val itemModel = mItems[position]
        if (itemModel.isItemSelected()) {
            mSelectedItemPosition = position
        }
        holder.bindData(itemModel)
    }

    fun updateItems(itemList: List<ItemModel>) {
        val diffResult = DiffUtil.calculateDiff(
            ItemDiffCallback(
                this.mItems,
                itemList
            )
        )
        this.mItems.clear()
        this.mItems.addAll(itemList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateSelectedItem(position: Int) {
        if (mItems.size == 0 || mSelectedItemPosition == position) return

        try {
            // reset old selected item
            mItems[mSelectedItemPosition].setItemSelected(false)
            // update new selected item
            mItems[position].setItemSelected(true)
            mSelectedItemPosition = position

            notifyDataSetChanged()
        } catch (e: Exception) {
            // catch out of index array
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].getHolderType()
    }

    private fun createHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemModel> {
        try {
            val clazz = mHolderTypeArray.get(viewType)
            return clazz.getConstructor(ViewGroup::class.java)
                .newInstance(parent) as BaseViewHolder<ItemModel>
        } catch (e: Exception) {
            Timber.w(">>>${this::class.java.simpleName} -> createHolder: " + e.toString())
        }

        return object : BaseViewHolder<ItemModel>(parent) {
            override fun bindData(item: ItemModel) {
                // Do Nothing
            }
        }
    }
}