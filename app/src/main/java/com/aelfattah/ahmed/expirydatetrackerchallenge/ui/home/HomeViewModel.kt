package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.home

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

class HomeViewModel constructor(private val goodsRepositoryImpl: GoodsRepositoryImpl) :
    ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _goods: MutableStateFlow<ArrayList<Item>> = MutableStateFlow(ArrayList())
    val goods: StateFlow<ArrayList<Item>> get() = _goods

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "error : $throwable")
    }

    init {
        getNonExpiredGoods()
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            goodsRepositoryImpl.insertItem(item)
        }
    }

    private fun getNonExpiredGoods() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = goodsRepositoryImpl.getGoodsByExpiration(false)
            _goods.emit(result as ArrayList<Item>)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}