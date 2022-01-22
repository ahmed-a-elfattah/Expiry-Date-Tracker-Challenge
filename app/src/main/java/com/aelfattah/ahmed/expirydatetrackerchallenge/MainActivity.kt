package com.aelfattah.ahmed.expirydatetrackerchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}