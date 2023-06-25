package com.example.employeetimefixation.presentation.ui.timetable.holders.tablelist

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.AttentionLevel
import com.example.employeetimefixation.presentation.model.TableItemModel

class TableListHolder(
    itemView: View,
    private val onItemModelListener: TableListHolderAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var listModel: TableItemModel? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(listModel: TableItemModel) {
        this.listModel = listModel
        itemView.apply {
            listModel.let {
                findViewById<TextView>(R.id.number).text = it.number
                findViewById<TextView>(R.id.name).text = it.name
                findViewById<TextView>(R.id.position).text = it.position
                findViewById<TextView>(R.id.time_from).text = it.timeFrom
                findViewById<TextView>(R.id.time_to).text = it.timeTo
                findViewById<TextView>(R.id.brake).setBrakeIcon(it.brake)
                changeColorOnAttentionLvl(it.attention)
            }
        }
    }

    private fun View.changeColorOnAttentionLvl(attentionLevel: AttentionLevel) {
        background = when(attentionLevel) {
            AttentionLevel.OK -> context.getDrawable(R.drawable.dr_line_ok)
            AttentionLevel.WARNING -> context.getDrawable(R.drawable.dr_line_warning)
            AttentionLevel.URGENT -> context.getDrawable(R.drawable.dr_line_urgent)
        }
    }

    private fun TextView.setBrakeIcon(brake: Boolean) {
        val checkBox = if (brake) {
            resources.getDrawable(R.drawable.ic_outline_check_box_24)
        } else {
            resources.getDrawable(R.drawable.ic_outline_check_box_outline_blank_24)
        }
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, checkBox)
    }

    override fun onClick(v: View?) {
        onItemModelListener.onItemClick(listModel)
    }
}