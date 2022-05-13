package com.example.onlinestoreapp.ui.fragments.dashboardfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.example.onlinestoreapp.databinding.DashboardFragmentBinding
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentBinding>() {

    private val viewModel: DashboardViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DashboardFragmentBinding = DashboardFragmentBinding.inflate(inflater,container,false)


}