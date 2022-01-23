package com.aelfattah.ahmed.expirydatetrackerchallenge.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(
    tableName = "goods_table", indices = [Index(
        value = ["barcode"],
        unique = true
    )]
)
@Parcelize
data class Item(
    @PrimaryKey val barcode: String,
    val name: String,
    val category: String,
    val expiryDate: Long,
    var expired: Boolean = false
) : Parcelable {
    val expirationDate: String get() = DateFormat.getTimeInstance().format(expiryDate)
}