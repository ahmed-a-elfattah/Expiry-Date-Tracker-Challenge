package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.FragmentHomeBinding
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    override val viewModel: HomeViewModel by inject()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            launch {
                for (i in 1..4) {
                    var item: Item
                    if (i >2) {
                        item = Item(
                            barcode = "barcode$i",
                            name = "name$i",
                            category = "category$i",
                            expiryDate = System.currentTimeMillis(),
                            expired = true
                        )
                    } else {
                        item = Item(
                            barcode = "barcode$i",
                            name = "name$i",
                            category = "category$i",
                            expiryDate = System.currentTimeMillis()
                        )
                    }

                    viewModel.addItem(item = item)
                }
            }
            launch {
                viewModel.goods.collect {
                    Log.e(TAG, "Goods: $it")
                }
            }
        }
    }
}