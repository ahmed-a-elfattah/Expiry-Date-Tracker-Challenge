package com.aelfattah.ahmed.expirydatetrackerchallenge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aelfattah.ahmed.expirydatetrackerchallenge.R
import com.aelfattah.ahmed.expirydatetrackerchallenge.ScannerActivity
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.ActivityMainBinding
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.addItem.AddItemSheet

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var resultLauncher: ActivityResultLauncher<Intent?>
    private var addItemSheet: AddItemSheet? = null

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

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val qrcode = it.data!!.extras!!.getString("qrcode")!!
                    openAddItemSheet(qrcode = qrcode)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_scan) resultLauncher.launch(
            Intent(
                applicationContext,
                ScannerActivity::class.java
            )
        )
        return super.onOptionsItemSelected(item)
    }

    private fun openAddItemSheet(qrcode: String) {
        if (addItemSheet != null && addItemSheet!!.isAdded) return
        addItemSheet = AddItemSheet()
        addItemSheet?.apply {
            arguments = Bundle().apply {
                putString(
                    "qrcode",
                    qrcode
                )
            }
            show(
                supportFragmentManager,
                AddItemSheet::class.java.name
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}