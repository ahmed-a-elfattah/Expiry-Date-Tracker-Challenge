package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.expiredGoods

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.FragmentExpiredGoodsBinding
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ExpiredGoodsFragment : BaseFragment<ExpiredGoodsViewModel, FragmentExpiredGoodsBinding>() {
    override val viewModel: ExpiredGoodsViewModel by inject()

    companion object {
        private const val TAG = "ExpiredGoodsFragment"
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentExpiredGoodsBinding =
        FragmentExpiredGoodsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.expiredGoods.collect {
                Log.e(TAG, "expiredGoods: $it")
            }
        }
    }
}