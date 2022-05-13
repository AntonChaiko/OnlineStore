package com.example.onlinestoreapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authenticationFragment -> hideSupportBar(true)
                R.id.registerFragment -> hideSupportBar(true)
                R.id.createProductFragment ->hideSupportBar(true)
                else -> hideSupportBar(false)
            }
        }


    }

    private fun hideSupportBar(hide: Boolean) {
        when (hide) {
            true -> {
                binding.bottomNavBar.visibility = View.GONE
                supportActionBar?.hide()
            }
            false -> {
                binding.bottomNavBar.visibility = View.VISIBLE
                supportActionBar?.show()
            }
        }
    }
}