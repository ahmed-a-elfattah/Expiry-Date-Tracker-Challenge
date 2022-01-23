package com.aelfattah.ahmed.expirydatetrackerchallenge.data.repository

import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.sources.local.GoodsDao

class GoodsRepositoryImpl constructor(private val goodsDao: GoodsDao) : GoodsRepository {

    override suspend fun insertItem(item: Item) = goodsDao.insertItem(item = item)

    override suspend fun updateItem(item: Item) = goodsDao.updateItem(item = item)

    override suspend fun getGoodsByExpiration(isExpired: Boolean) =
        goodsDao.getGoodsByExpiration(isExpired = isExpired)
}