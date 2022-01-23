package com.aelfattah.ahmed.expirydatetrackerchallenge.data.repository

import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import kotlinx.coroutines.flow.Flow

interface GoodsRepository {

    suspend fun insertItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun getGoodsByExpiration(isExpired:Boolean): List<Item>
}