package com.example.employeetimefixation.presentation.ui.details.holder.positions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R

class PositionsListHolderAdapter : RecyclerView.Adapter<PositionsListItemHolder>() {

    private var items: MutableList<String> = mutableListOf()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsListItemHolder {
        context = parent.context
        return PositionsListItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.position_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PositionsListItemHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    private fun PositionsListItemHolder.bind(item: String?) {
        item?.let { bind(it) }
    }

    fun setList(list: List<String>?): PositionsListHolderAdapter {
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
        return this
    }
}