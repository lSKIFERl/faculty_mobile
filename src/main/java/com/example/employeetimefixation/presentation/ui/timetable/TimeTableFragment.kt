package com.example.employeetimefixation.presentation.ui.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.employeetimefixation.R
import com.example.employeetimefixation.databinding.FragmentTimeTableBinding
import com.example.employeetimefixation.presentation.model.TableItemModel
import com.example.employeetimefixation.presentation.ui.details.EmployeeDetailFragment
import com.example.employeetimefixation.presentation.ui.timetable.holders.tablelist.TableListHolderAdapter
import com.example.employeetimefixation.presentation.util.SortType

class TimeTableFragment : Fragment() {

    private lateinit var timeTableViewModel: TimeTableViewModel
    private lateinit var binding: FragmentTimeTableBinding
    private val tableListAdapter = TableListHolderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFilter()
        observeLiveData()
    }

    private fun initView() {
        tableListAdapter.let {
            it.setItemListener(object :
                TableListHolderAdapter.OnItemListener {
                override fun onItemClick(item: TableItemModel?) {
                    findNavController().navigate(
                        R.id.nav_employee_details,
                        bundleOf(EmployeeDetailFragment.EMPLOYEE_ID_INTENT to item?.personalNumber)
                    )
                }
            })
            binding.employeeList.adapter = it
        }
    }

    private fun initFilter() {
        binding.toolbarFilter.run {
            number.apply {
                text = "№"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.NUMBER)
            }
            name.apply {
                text = "Имя"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.NAME)
            }
            position.apply {
                text = "Позиция"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.POSITION)
            }
            timeFrom.apply {
                text = "С"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.TIME_FROM)
            }
            timeTo.apply {
                text = "До"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.TIME_TO)
            }
            brake.apply {
                text = "Перерыв"
                setIcon(R.drawable.ic_empty)
                clickAndSort(SortType.BRAKE)
            }
        }
    }

    private fun TextView.setIcon(drawable: Int) {
        val img = context.resources.getDrawable(drawable)
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, img)
    }

    private fun observeLiveData() {
        timeTableViewModel =
            ViewModelProvider(this)[TimeTableViewModel::class.java]

        timeTableViewModel.employeeList.observe(viewLifecycleOwner, {
            tableListAdapter.setList(it)
        })
    }
}
