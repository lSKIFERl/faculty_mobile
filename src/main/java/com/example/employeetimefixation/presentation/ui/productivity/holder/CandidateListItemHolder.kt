package com.example.employeetimefixation.presentation.ui.productivity.holder

import android.view.View
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetimefixation.R
import com.example.employeetimefixation.presentation.model.CandidateModel
import com.example.employeetimefixation.presentation.util.convertToString
import java.sql.Time
import java.sql.Timestamp
import java.util.*

class CandidateListItemHolder(
    itemView: View,
    private val onItemModelListener: CandidateListHolderAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var model: CandidateModel? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(model: CandidateModel) {
        this.model = model
        itemView.apply {
            model.let { m ->
                findViewById<TextView>(R.id.tv_primary_info_candidate).text =
                    String.format(
                        context.getString(R.string.primary_info),
                        m.number,
                        m.name,
                        m.position
                    )
                findViewById<TextView>(R.id.tv_additional_info_candidate).text =
                    String.format(
                        context.getString(R.string.additional_info_candidate),
                        m.expirationTime.convertToString(),
                        timeRemains().convertToString()
                    )
                val listBrakes = mutableListOf<Boolean>()
                for (i: Int in 0 until (m.brakes?.size ?: 0) step 2) {
                    if (m.brakes?.get(i) != null) {
                        listBrakes.add(true)
                    } else {
                        listBrakes.add(false)
                    }
                }

                findViewById<CheckedTextView>(R.id.brake_1).apply {
                    isChecked = listBrakes[0]
                    isClickable = !listBrakes[0]
                    if (isClickable) {
                        setOnClickListener {
                            this@CandidateListItemHolder.model = m.copy(
                                brakes = m.brakes?.toMutableList()?.apply {
                                    set(0, Timestamp(System.currentTimeMillis()))
                                    set(1, Timestamp(System.currentTimeMillis()).addMinutes(30))
                                }
                            )
                            onItemModelListener.onInfoChanged(this@CandidateListItemHolder.model)
                            initTimeAdditionalInfo()
                        }
                    }
                }
                findViewById<CheckedTextView>(R.id.brake_2).apply {
                    isChecked = listBrakes[1]
                    isClickable = !listBrakes[1]
                    if (isClickable) {
                        setOnClickListener {
                            this@CandidateListItemHolder.model = m.copy(
                                brakes = m.brakes?.toMutableList()?.apply {
                                    set(2, Timestamp(System.currentTimeMillis()))
                                    set(3, Timestamp(System.currentTimeMillis()).addMinutes(15))
                                }
                            )
                            onItemModelListener.onInfoChanged(this@CandidateListItemHolder.model)
                            initTimeAdditionalInfo()
                        }
                    }
                }
                findViewById<CheckedTextView>(R.id.brake_3).apply {
                    isChecked = listBrakes[2]
                    isClickable = !listBrakes[2]
                    if (isClickable) {
                        setOnClickListener {
                            this@CandidateListItemHolder.model = m.copy(
                                brakes = m.brakes?.toMutableList()?.apply {
                                    set(4, Timestamp(System.currentTimeMillis()))
                                    set(5, Timestamp(System.currentTimeMillis()).addMinutes(15))
                                }
                            )
                            onItemModelListener.onInfoChanged(this@CandidateListItemHolder.model)
                            initTimeAdditionalInfo()
                        }
                    }
                }
                findViewById<CheckedTextView>(R.id.end_work).apply {
                    setOnClickListener {
                        this@CandidateListItemHolder.model = m.copy(
                            endWork = isChecked
                        )
                        onItemModelListener.onInfoChanged(this@CandidateListItemHolder.model)
                    }
                }
            }
            findViewById<CheckBox>(R.id.btn_expand_item).apply {
                val visibility = if (isChecked) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                findViewById<CheckBox>(R.id.end_work).visibility = visibility
                findViewById<CheckBox>(R.id.brakes_add_info).visibility = visibility
            }
        }
    }

    private fun View.initTimeAdditionalInfo() {
        findViewById<TextView>(R.id.brake_1_add_info).text = String.format(
            "%s\n%s",
            model?.brakes?.get(0)?.convertToString(), model?.brakes?.get(1)?.convertToString()
        )
        findViewById<TextView>(R.id.brake_2_add_info).text = String.format(
            "%s\n%s",
            model?.brakes?.get(2)?.convertToString(), model?.brakes?.get(3)?.convertToString()
        )
        findViewById<TextView>(R.id.brake_3_add_info).text = String.format(
            "%s\n%s",
            model?.brakes?.get(4)?.convertToString(), model?.brakes?.get(5)?.convertToString()
        )
    }

    private fun Timestamp.addMinutes(minutes: Int): Timestamp {
        return Timestamp(Time(this.hours, this.minutes + minutes, 0).time)
    }

    private fun timeRemains(): Timestamp {
        val hours = ((model?.expirationTime?.hours ?: 0) - Time(System.currentTimeMillis()).hours)
        val minutes =
            ((model?.expirationTime?.minutes ?: 0) - Time(System.currentTimeMillis()).minutes)
        return Timestamp(Time(hours, minutes, 0).time)
    }

    override fun onClick(v: View?) {
        onItemModelListener.onItemClick(model)
    }
}