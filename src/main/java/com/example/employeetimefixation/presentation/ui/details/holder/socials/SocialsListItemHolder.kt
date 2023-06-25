package com.example.employeetimefixation.presentation.ui.details.holder.socials

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.SocialModel
import com.example.employeetimefixation.presentation.model.SocialsIcons

class SocialsListItemHolder(
    itemView: View,
    private val onItemModelListener: SocialsListHolderAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var model: SocialModel? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(model: SocialModel) {
        this.model = model
        (itemView as TextView).apply {
            text = model.text
            setIcon(model.icon)
        }
    }

    private fun TextView.setIcon(brake: SocialsIcons?) {
        val icon = resources.getDrawable(brake?.drawable?: R.drawable.ic_empty)
        setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
    }

    override fun onClick(v: View?) {
        onItemModelListener.onItemClick(model?.link)
    }
}