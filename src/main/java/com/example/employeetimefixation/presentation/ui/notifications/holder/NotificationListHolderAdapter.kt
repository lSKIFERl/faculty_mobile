package com.example.employeetimefixation.presentation.ui.notifications.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.AttentionLevel
import com.example.employeetimefixation.presentation.model.NotificationModel
import com.example.employeetimefixation.presentation.model.NotificationsSettingsModel

class NotificationListHolderAdapter : RecyclerView.Adapter<NotificationListItemHolder>() {

    private lateinit var itemListener: OnItemListener

    private var items: MutableList<NotificationModel> = mutableListOf()

    private var originList: MutableList<NotificationModel> = mutableListOf()

    private lateinit var context: Context

    private val settings = NotificationsSettingsModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListItemHolder {
        context = parent.context
        return NotificationListItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.notification_list_item,
                parent,
                false
            ),
            itemListener
        )
    }

    override fun onBindViewHolder(holder: NotificationListItemHolder, position: Int) {
        holder.bind(items.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = items.size

    private fun NotificationListItemHolder.bind(item: NotificationModel?) {
        item?.let { bind(it) }
    }

    private fun replaceList(list: List<NotificationModel>?) {
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

    fun sortASC(): Boolean {
        changeSettings(sortASC = !settings.sortASC)
        return !settings.sortASC
    }

    fun changeSettings(
        sortASC: Boolean? = null,
        showUrgent: Boolean? = null,
        showWarning: Boolean? = null,
        showOk: Boolean? = null
    ) {

        settings.apply {
            this.sortASC = sortASC ?: this.sortASC
            this.showUrgent = showUrgent ?: this.showUrgent
            this.showWarning = showWarning ?: this.showWarning
            this.showOk = showOk ?: this.showOk
        }

        val filteredList = originList.filter {
            when (it.attentionLevel) {
                AttentionLevel.URGENT -> settings.showUrgent
                AttentionLevel.WARNING -> settings.showWarning
                AttentionLevel.OK -> settings.showOk
            }
        }


        replaceList(filteredList.run {
            if (settings.sortASC) sortedBy { it.created } else sortedByDescending { it.created }
        })
    }

    fun setList(list: List<NotificationModel>?): NotificationListHolderAdapter {
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
        fun onItemClick(item: NotificationModel?)
    }
}