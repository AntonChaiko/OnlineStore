package com.example.onlinestoreapp.ui.fragments.profilefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.ProfileFragmentBinding
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {

    private val viewModel: ProfileViewModel by viewModels()


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.isEditable.observe(viewLifecycleOwner) {
            isEditableFields(it)
        }

        binding.editUserDataButton.setOnClickListener {
            viewModel.makeFieldsEditable()
            binding.nameEditText.requestFocus()
        }
        binding.addProductButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_createProductFragment)
        }

        viewModel.authState.observe(viewLifecycleOwner) { state ->
            progressBarVisibility(state.isLoading)
            when {
                state.error.isNotBlank() -> toast(state.error)
            }
        }
    }

    private fun isEditableFields(isEditable: Boolean) {
        binding.apply {
            emailEditText.isEnabled = isEditable
            nameEditText.isEnabled = isEditable
            numberEditText.isEnabled = isEditable
            secondNameEditText.isEnabled = isEditable
        }
        binding.setupUserButton.visibility = if (isEditable) View.VISIBLE else View.GONE
    }

    private fun progressBarVisibility(visible: Boolean) {
        binding.loadingProgressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProfileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)


}