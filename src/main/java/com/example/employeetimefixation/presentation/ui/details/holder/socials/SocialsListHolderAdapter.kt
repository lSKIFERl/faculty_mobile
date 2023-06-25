package com.example.employeetimefixation.presentation.ui.details.holder.socials

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.SocialModel

class SocialsListHolderAdapter : RecyclerView.Adapter<SocialsListItemHolder>() {

    private lateinit var itemListener: OnItemListener

    private var items: MutableList<SocialModel> = mutableListOf()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialsListItemHolder {
        context = parent.context
        return SocialsListItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.socials_list_item,
                parent,
                false
            ),
            itemListener
        )
    }

    override fun onBindViewHolder(holder: SocialsListItemHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    private fun SocialsListItemHolder.bind(item: SocialModel?) {
        item?.let { bind(it) }
    }

    fun setList(list: List<SocialModel>?): SocialsListHolderAdapter {
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

    fun setItemListener(itemListener: OnItemListener) {
        itemListener.also { this.itemListener = it }
    }

    interface OnItemListener {
        fun onItemClick(item: String?)
    }
}