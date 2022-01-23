package com.aelfattah.ahmed.expirydatetrackerchallenge.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item

@Database(entities = [Item::class], version = 1)
abstract class GoodsDatabase : RoomDatabase() {

    abstract fun getGoodsDao(): GoodsDao
}