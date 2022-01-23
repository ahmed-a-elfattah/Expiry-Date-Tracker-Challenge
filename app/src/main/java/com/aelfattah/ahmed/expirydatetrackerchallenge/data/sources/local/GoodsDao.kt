package com.aelfattah.ahmed.expirydatetrackerchallenge.data.sources.local

import androidx.room.*
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item

@Dao
interface GoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Query("SELECT * FROM goods_table WHERE expired =:isExpired ORDER BY expiryDate DESC")
    suspend fun getGoodsByExpiration(isExpired: Boolean): List<Item>
}