package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.expiredGoods

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.repository.GoodsRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpiredGoodsViewModel(private val goodsRepositoryImpl: GoodsRepositoryImpl) :
    ViewModel() {

    companion object {
        private const val TAG = "ExpiredGoodsViewModel"
    }

    private var _expiredGoods: MutableStateFlow<ArrayList<Item>> = MutableStateFlow(ArrayList())
    val expiredGoods: StateFlow<ArrayList<Item>> get() = _expiredGoods

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "error : $throwable")
    }

    init {
        getExpiredGoods()
    }

    private fun getExpiredGoods() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = goodsRepositoryImpl.getGoodsByExpiration(true)
            _expiredGoods.emit(result as ArrayList<Item>)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}