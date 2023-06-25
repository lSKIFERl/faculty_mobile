package com.example.employeetimefixation.presentation.ui.productivity.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.CandidateModel
import com.example.employeetimefixation.presentation.util.lastMinusNow
import com.example.employeetimefixation.presentation.util.nonNullCount
import com.example.employeetimefixation.presentation.util.nullCount

class CandidateListHolderAdapter : RecyclerView.Adapter<CandidateListItemHolder>() {

    private lateinit var itemListener: OnItemListener

    private var items: MutableList<CandidateModel> = mutableListOf()

    private var originList: MutableList<CandidateModel> = mutableListOf()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateListItemHolder {
        context = parent.context
        return CandidateListItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.candidate_list_item,
                parent,
                false
            ),
            itemListener
        )
    }

    override fun onBindViewHolder(holder: CandidateListItemHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    private fun CandidateListItemHolder.bind(item: CandidateModel?) {
        item?.let { bind(it) }
    }

    private fun replaceList(list: List<CandidateModel>?) {
        items.clear()
        if (list != null) {
            items.addAll(list)
            items.sortWith(compareBy (
                { it.efficiency },
                { it.expirationTime },
                { it.brakes?.nullCount() },
                { it.brakes?.lastMinusNow() }
            ))
        } else {
            Toast.makeText(
                context,
                "Error while updating the list",
                Toast.LENGTH_LONG
            ).show()
        }
        notifyDataSetChanged()
    }

    fun setList(list: List<CandidateModel>?): CandidateListHolderAdapter {
        if (list != null) {
            originList.addAll(list)
        }
        replaceList(list)
        return this
    }

    fun setItemListener(itemListener: OnItemListener) {
        itemListener.also { this.itemListener = it }
    }

    interface OnItemListener {
        fun onItemClick(item: CandidateModel?)
        fun onInfoChanged(item: CandidateModel?)
    }
}