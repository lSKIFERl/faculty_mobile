package com.example.employeetimefixation.presentation.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.employeetimefixation.MainActivity
import com.example.employeetimefixation.R
import com.example.employeetimefixation.databinding.FragmentEmployeeDetailBinding
import com.example.employeetimefixation.presentation.model.EmployeeDetailsModel
import com.example.employeetimefixation.presentation.ui.details.holder.positions.PositionsListHolderAdapter
import com.example.employeetimefixation.presentation.ui.details.holder.socials.SocialsListHolderAdapter
import com.example.employeetimefixation.presentation.util.convertToString
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener


class EmployeeDetailFragment : Fragment(R.layout.fragment_employee_detail) {

    private lateinit var binding: FragmentEmployeeDetailBinding

    private lateinit var viewModel: EmployeeDetailViewModel

    private val positionsAdapter = PositionsListHolderAdapter()

    private val socialsAdapter = SocialsListHolderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setupActionBar(binding.detailsToolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun initView() {
        binding.apply {
            appBarDetails.addOnOffsetChangedListener(OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == -binding.appBarDetails.height + binding.detailsToolbar.height) {
                binding.toolbarAvatar.visibility = View.VISIBLE
            } else {
                binding.toolbarAvatar.visibility = GONE
            }
        })
            positions.adapter = positionsAdapter

            socialsAdapter.also {
                it.setItemListener(object : SocialsListHolderAdapter.OnItemListener {
                    override fun onItemClick(item: String?) {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item))
                        startActivity(browserIntent)
                    }
                })
                socialNets.adapter = it
            }
        }
    }

    private fun observeData() {
        val id = arguments?.getInt(EMPLOYEE_ID_INTENT) ?: error(
            0
        )
        viewModel.run {
            getDetailsById(id)
            employeeDetails.observe(viewLifecycleOwner, {
                setupView(it)
            })
        }
    }

    private fun setupView(details: EmployeeDetailsModel) {
        binding.apply {
            details.also {
                firstName.text = it.firstName
                lastname.text = it.lastName
                patronymic.text = it.patronymic

                num.text = it.number.toString()
                personalNumber.text = it.personalNumber.toString()
                pos.text = it.todayPositions?.joinToString()

                negativeNotes.text = it.negativeNotes.toString()
                positiveNotes.text = it.positiveNotes.toString()
                kln.text = it.kln.toString()

                positionsAdapter.setList(it.availablePositions)
                coef.text = it.productivity.toString()
                socialsAdapter.setList(it.socialNets)
            }
        }
        setupTable(details)
    }

    private fun setupTable(details: EmployeeDetailsModel) {
        binding.apply {
            details.also {
                cellSheetFrom.text = it.timeFromSchedule?.convertToString()
                cellSheetTo.text = it.timeToSchedule?.convertToString()
                if (it.timeFromFact == null && it.timeToFact == null) {
                    detailsRowFact.visibility = GONE
                } else {
                    cellFactFrom.text = it.timeFromFact?.convertToString()
                    cellFactTo.text = it.timeToFact?.convertToString()
                }

                if (it.breaks?.get(0) == null && it.breaks?.get(1) == null) {
                    detailsRowBrake1.visibility = GONE
                } else {
                    cellBrake1From.text = it.breaks[0].convertToString()
                    cellBrake1To.text = it.breaks[1].convertToString()
                }

                if (it.breaks?.get(2) == null && it.breaks?.get(3) == null) {
                    detailsRowBrake2.visibility = GONE
                } else {
                    cellBrake2From.text = it.breaks[2].convertToString()
                    cellBrake2To.text = it.breaks[3].convertToString()
                }

                if (it.breaks?.get(4) == null && it.breaks?.get(5) == null) {
                    detailsRowBrake3.visibility = GONE
                } else {
                    cellBrake3From.text = it.breaks[4].convertToString()
                    cellBrake3To.text = it.breaks[5].convertToString()
                }
            }
        }
    }

    override fun onPause() {
        (requireActivity() as MainActivity).setupActionBar(requireActivity().findViewById(R.id.toolbar))
        super.onPause()
    }

    companion object {
        const val EMPLOYEE_ID_INTENT = "EMPLOYEE_ID"
    }
}