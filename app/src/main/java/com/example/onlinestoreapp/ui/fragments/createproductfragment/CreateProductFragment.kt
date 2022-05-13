package com.example.onlinestoreapp.ui.fragments.createproductfragment

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentSender
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.CreateProductFragmentBinding
import com.example.onlinestoreapp.domain.models.ProductListState
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateProductFragment : BaseFragment<CreateProductFragmentBinding>() {

    private val viewModel: CreateProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.productListState.observe(viewLifecycleOwner) {
            when {
                it.isLoading -> progressBarIsLoading(it.isLoading)
                it.isProductAdded -> findNavController().navigate(R.id.action_createProductFragment_to_dashboardFragment)
                it.error.isNotEmpty() -> toast(it.error)
            }
        }

        binding.addProductImageButton.setOnClickListener {
            setupIntents()
        }
    }

    private fun setupIntents() {
        val getContentIntent = Intent(Intent.ACTION_GET_CONTENT)
        getContentIntent.type = "image/*"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val chs = Intent.createChooser(getContentIntent, "Open in...")
        chs.putExtra(Intent.EXTRA_INTENT, cameraIntent)
        val intentArray = arrayOf(getContentIntent, cameraIntent)
        chs.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)

        resultLauncher.launch(chs)

    }

    private fun progressBarIsLoading(isLoading: Boolean) {
        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Uri? = result.data?.data
                val imageBitmap: Bitmap = result.data?.extras?.get("data") as Bitmap
                when {
                    data != null -> binding.productImage.setImageURI(data)
                    else -> {
                        binding.productImage.setImageBitmap(imageBitmap)
                    }
                }
            }


        }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CreateProductFragmentBinding =
        CreateProductFragmentBinding.inflate(inflater, container, false)


}