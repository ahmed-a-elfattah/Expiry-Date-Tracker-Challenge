package com.aelfattah.ahmed.expirydatetrackerchallenge.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.model.Item
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.ItemRowBinding

class GoodsAdapter :
    RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {
    private var list: ArrayList<Item> = ArrayList()

    inner class GoodsViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder =
        GoodsViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.binding.item = list[position]
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }
}