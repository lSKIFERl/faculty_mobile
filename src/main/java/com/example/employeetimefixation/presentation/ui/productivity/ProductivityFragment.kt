package com.example.employeetimefixation.presentation.ui.productivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.employeetimefixation.R
import com.example.employeetimefixation.databinding.FragmentProductivityBinding
import com.example.employeetimefixation.presentation.model.CandidateModel
import com.example.employeetimefixation.presentation.ui.details.EmployeeDetailFragment
import com.example.employeetimefixation.presentation.ui.productivity.holder.CandidateListHolderAdapter
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


class ProductivityFragment : Fragment(R.layout.fragment_productivity) {

    private lateinit var series1: LineGraphSeries<DataPoint>
    private var series2: LineGraphSeries<DataPoint>? = null
    private lateinit var productivityViewModel: ProductivityViewModel
    private lateinit var binding: FragmentProductivityBinding
    private var candidateList: MutableList<CandidateModel> = mutableListOf()
    private val candidateListHolderAdapter = CandidateListHolderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLineChart()
        observeData()
    }

    private fun initView() {
        candidateListHolderAdapter.let {
            it.setItemListener(object :
                CandidateListHolderAdapter.OnItemListener {
                override fun onItemClick(item: CandidateModel?) {
                    findNavController().navigate(
                        R.id.nav_employee_details,
                        bundleOf(EmployeeDetailFragment.EMPLOYEE_ID_INTENT to item?.personalNumber)
                    )
                }

                override fun onInfoChanged(item: CandidateModel?) {
                    editCandidatesList(item)
                }
            })
            binding.candidates.adapter = it
        }
    }

    private fun initLineChart() {

        series1 = LineGraphSeries(
            arrayOf(
                DataPoint(Date(2022, 4,14, 10, 0), 4.5),
                DataPoint(Date(2022, 4,14, 12, 0), 6.7),
                DataPoint(Date(2022, 4,14, 14, 0), 7.9),
                DataPoint(Date(2022, 4,14, 16, 0), 6.8),
                DataPoint(Date(2022, 4,14, 18, 0), 10.3),
                DataPoint(Date(2022, 4,14, 20, 0), 16.2)
            )
        )

        series1.color = R.color.extra
        binding.chart.viewport.setMaxY(17.0)
        binding.chart.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(context, SimpleDateFormat("HH:mm"))
        binding.chart.addSeries(series1)

        editChart()
    }

    private fun editCandidatesList(item: CandidateModel?) {
        candidateList.removeIf {
            it.personalNumber == item?.personalNumber
        }
        item?.let { candidateList.add(it) }
        editChart()
    }

    private fun editChart() {
        //переписывание чарта/добавление новой точки
        if (series2 != null) {
            binding.chart.removeSeries(series2)
        }

        var efficiency = 0f
        val brakes: SortedMap<Date, Float> = sortedMapOf()
        for (candidate in candidateList) {
            efficiency += candidate.efficiency
            for (i in candidate.brakes?.indices!! step 2) {
                val brakeEnd = if (candidate.brakes[i+1] == null) {
                    if (i + 1 == 5) {
                        Time((candidate.brakes[i]?.time ?: Time(System.currentTimeMillis()).time)
                                + Time(0,30,0).time)
                    } else {
                        Time((candidate.brakes[i]?.time ?: Time(System.currentTimeMillis()).time)
                                + Time(0,15,0).time)
                    }
                } else {
                    candidate.brakes[i+1]
                }
                if (brakes.containsKey(candidate.brakes[i])) {
                    brakes.apply {
                        set(candidate.brakes[i], get(candidate.brakes[i])?.plus(candidate.efficiency))
                        if (!candidate.endWork) {
                            set(brakeEnd, get(brakeEnd)?.minus(candidate.efficiency))
                        }
                    }
                } else {
                    brakes[candidate.brakes[i]] = candidate.efficiency
                    if (!candidate.endWork) {
                        brakes[brakeEnd] = -candidate.efficiency
                    }
                }
            }
            if (candidate.endWork) {
                efficiency -= candidate.efficiency
            }
        }

        val points: Array<DataPoint> = arrayOf()
        for (brake in brakes) {
            points.plusElement(DataPoint(brake.key, (efficiency - brake.value).toDouble()))
        }
        series2 = LineGraphSeries(points)

        binding.chart.addSeries(series2)
    }

    private fun observeData() {
        productivityViewModel =
            ViewModelProvider(this)[ProductivityViewModel::class.java]

        productivityViewModel.candidatesList.observe(viewLifecycleOwner, {
            candidateList = it.toMutableList()
            candidateListHolderAdapter.setList(it)
        })
    }

}