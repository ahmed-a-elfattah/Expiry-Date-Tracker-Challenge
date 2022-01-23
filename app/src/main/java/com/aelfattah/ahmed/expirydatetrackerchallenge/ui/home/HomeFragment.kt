package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.FragmentHomeBinding
import com.aelfattah.ahmed.expirydatetrackerchallenge.utils.BaseFragment
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.GoodsAdapter
import com.aelfattah.ahmed.expirydatetrackerchallenge.utils.PeriodicBackgroundNotification
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private lateinit var goodsAdapter: GoodsAdapter

    override val viewModel: HomeViewModel by inject()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goodsAdapter = GoodsAdapter()
        binding.rvGoods.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = goodsAdapter
        }

        lifecycleScope.launchWhenCreated {
            launch {
                for (i in 1..4) {
                    var item: Item
                    if (i > 2) {
                        item = Item(
                            barcode = "barcode$i",
                            name = "name$i",
                            category = "category$i",
                            expiryDate = System.currentTimeMillis() + (10000 * i),
                            expired = true
                        )
                    } else {
                        item = Item(
                            barcode = "barcode$i",
                            name = "name$i",
                            category = "category$i",
                            expiryDate = System.currentTimeMillis() + (10000 * i)
                        )
                    }

                    viewModel.addItem(item = item)
                }
            }
            launch {
                viewModel.goods.collect {
                    Log.e(TAG, "Goods: $it")
                    goodsAdapter.setList(it)
                    for (item in it)
                        periodWork(item = item)
                }
            }
        }
    }

    private fun periodWork(item: Item) {
        val periodWork = PeriodicWorkRequest.Builder(
            PeriodicBackgroundNotification::class.java, item.expiryDate,
            TimeUnit.MINUTES
        )
            .addTag("periodic-pending-notification")
            .build()
        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
            "periodic-pending-notification",
            ExistingPeriodicWorkPolicy.KEEP,
            periodWork
        )
    }

}