package com.aelfattah.ahmed.expirydatetrackerchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aelfattah.ahmed.expirydatetrackerchallenge.R
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.bnvHome.apply {
            itemIconTintList = null
            setupWithNavController(navController = navController)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}