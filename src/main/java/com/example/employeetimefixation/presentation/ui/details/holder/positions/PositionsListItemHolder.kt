package com.example.employeetimefixation.presentation.ui.details.holder.positions

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PositionsListItemHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: String) {
        (itemView as TextView).text = model
    }
}