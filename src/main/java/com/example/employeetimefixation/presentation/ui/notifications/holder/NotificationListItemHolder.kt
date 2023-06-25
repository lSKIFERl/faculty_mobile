package com.example.employeetimefixation.presentation.ui.notifications.holder

import android.view.View
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.AttentionLevel
import com.example.employeetimefixation.presentation.model.NotificationModel

class NotificationListItemHolder(
    itemView: View,
    private val onItemModelListener: NotificationListHolderAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var model: NotificationModel? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(model: NotificationModel) {
        this.model = model
        itemView.apply {
            model.let {
                findViewById<TextView>(R.id.tv_primary_info).text =
                    String.format(context.getString(R.string.primary_info),
                        it.number,
                        it.name,
                        it.position
                    )
                findViewById<TextView>(R.id.tv_additional_info).text = it.message
                changeColorOnAttentionLvl(it.attentionLevel)
            }
        }
    }

    private fun View.changeColorOnAttentionLvl(attentionLevel: AttentionLevel) {
        DrawableCompat.setTint(background, when(attentionLevel) {
            AttentionLevel.OK -> context.getColor(R.color.ok)
            AttentionLevel.WARNING -> context.getColor(R.color.warning)
            AttentionLevel.URGENT -> context.getColor(R.color.extra)
        })
    }

    override fun onClick(v: View?) {
        onItemModelListener.onItemClick(model)
    }
}