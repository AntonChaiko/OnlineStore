package com.example.onlinestoreapp.ui.fragments.favoritesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlinestoreapp.databinding.FavoritesFragmentBinding
import com.example.onlinestoreapp.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {


    private val viewModel: FavoritesViewModel by viewModels()



    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FavoritesFragmentBinding  = FavoritesFragmentBinding.inflate(inflater,container,false)


}