package com.example.onlinestoreapp.ui.fragments.authenticationfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.AuthenticationFragmentBinding
import com.example.onlinestoreapp.domain.models.AuthListState
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthenticationFragmentBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.authState.observe(viewLifecycleOwner) { state ->
            progressBarVisibility(state.isLoading)
            when {
                state.isRegistrationComplete -> findNavController().navigate(R.id.action_authenticationFragment_to_dashboardFragment)
                state.error.isNotBlank() -> toast(state.error)
            }
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_authenticationFragment_to_registerFragment)
        }
    }
    private fun progressBarVisibility(visible: Boolean) {
        binding.loginProgressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): AuthenticationFragmentBinding =
        AuthenticationFragmentBinding.inflate(inflater, container, false)

}