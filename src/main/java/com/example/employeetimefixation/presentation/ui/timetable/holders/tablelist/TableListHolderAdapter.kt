package com.example.employeetimefixation.presentation.ui.timetable.holders.tablelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.util.SortType
import com.example.employeetimefixation.presentation.model.TableItemModel
import com.example.employeetimefixation.presentation.util.SortingFilter

class TableListHolderAdapter: RecyclerView.Adapter<TableListHolder>() {

    private lateinit var itemListener: OnItemListener

    private var items: MutableList<TableItemModel> = mutableListOf()

    private lateinit var context: Context

    private lateinit var sorter: SortingFilter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableListHolder {
        context = parent.context
        return TableListHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_blank_item,
                parent,
                false
            ),
            itemListener
        )
    }

    override fun onBindViewHolder(holder: TableListHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    private fun TableListHolder.bind(item: TableItemModel?) {
        item?.let { bind(it) }
    }

    fun sortListBy(sort: SortType): Int {
        val callCount = sorter.addSortState(sort)
        replaceList(sorter.sort())
        return callCount
    }

    private fun replaceList(list: List<TableItemModel>?) {
        items.clear()
        if (list != null) {
            items.addAll(list)
        } else {
            Toast.makeText(
                context,
                "Error while updating the list",
                Toast.LENGTH_LONG
            ).show()
        }
        notifyDataSetChanged()
    }

    fun setList(list: List<TableItemModel>?): TableListHolderAdapter {
        replaceList(list)
        sorter = SortingFilter(items)
        notifyDataSetChanged()
        return this
    }

    fun setItemListener(itemListener: OnItemListener) {
        itemListener.also { this.itemListener = it }
    }

    interface OnItemListener {
        fun onItemClick(item: TableItemModel?)
    }
}