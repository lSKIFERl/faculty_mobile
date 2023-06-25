package com.example.employeetimefixation.presentation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.employeetimefixation.R
import com.example.employeetimefixation.databinding.FragmentNotificationsBinding
import com.example.employeetimefixation.presentation.model.NotificationModel
import com.example.employeetimefixation.presentation.ui.details.EmployeeDetailFragment
import com.example.employeetimefixation.presentation.ui.notifications.holder.NotificationListHolderAdapter

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding
    private val notificationsAdapter = NotificationListHolderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
    }

    private fun initView() {
        notificationsAdapter.let {
            it.setItemListener(object :
                NotificationListHolderAdapter.OnItemListener {
                override fun onItemClick(item: NotificationModel?) {
                    findNavController().navigate(
                        R.id.nav_employee_details,
                        bundleOf(EmployeeDetailFragment.EMPLOYEE_ID_INTENT to item?.personalNumber)
                    )
                }
            })
            binding.notificationsList.adapter = it
        }

        binding.apply {
            btnNewOld.run{
                setOnClickListener {
                    if(notificationsAdapter.sortASC()) {
                        text = "Сначала новые"
                    } else {
                        text = "Сначала старые"
                    }
                }
                cbExtra.run {
                    setOnClickListener {
                        isChecked = !isChecked
                        notificationsAdapter.changeSettings(
                            showUrgent = isChecked
                        )
                    }
                }
                cbWarning.run {
                    setOnClickListener {
                        isChecked = !isChecked
                        notificationsAdapter.changeSettings(
                            showWarning = isChecked
                        )
                    }
                }
                cbOk.run {
                    setOnClickListener {
                        isChecked = !isChecked
                        notificationsAdapter.changeSettings(
                            showOk = isChecked
                        )
                    }
                }
            }
        }
    }

    private fun observeLiveData() {
        notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        notificationsViewModel.notificationList.observe(viewLifecycleOwner, {
            notificationsAdapter.setList(it)
        })
    }
}