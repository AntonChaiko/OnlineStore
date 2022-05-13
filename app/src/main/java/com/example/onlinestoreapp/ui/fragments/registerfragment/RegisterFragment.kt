package com.example.onlinestoreapp.ui.fragments.registerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.RegisterFragmentBinding
import com.example.onlinestoreapp.domain.models.AuthListState
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import com.example.onlinestoreapp.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.registerButton.setOnClickListener {
            viewModel.registerUser()
        }
        viewModel.authState.observe(viewLifecycleOwner) {
            updateUI(it)
        }


    }

    private fun updateUI(state: AuthListState) {
        binding.isloadingProgressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        if (state.isRegistrationComplete) findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)
        if (state.error.isNotBlank()) toast(state.error)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegisterFragmentBinding = RegisterFragmentBinding.inflate(inflater, container, false)


}